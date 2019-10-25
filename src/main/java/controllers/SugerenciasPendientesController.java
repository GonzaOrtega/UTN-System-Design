package controllers;


import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import domain.RepositorioDeUsuarios;
import domain.Usuario;
import domain.enums.TipoSugerencias;
import domain.Sugerencia;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class SugerenciasPendientesController implements WithGlobalEntityManager, TransactionalOps{
	private List<Sugerencia> sugerencias=null;
	
	
	////////////METODOS_QUE_USA_POST_INICIANDO_EN_confirmarSugerencia/////////////////////////////
	private void cambiarEstados(Sugerencia sugerenciaAceptada) {
		withTransaction(()->{
			sugerenciaAceptada.setEstado(TipoSugerencias.ACEPTADA);
			sugerencias.remove(sugerenciaAceptada);
			sugerencias.forEach(sug->sug.setEstado(TipoSugerencias.RECHAZADA));
		});
		entityManager().close();
	}
	private boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		}catch (Exception e) {
			return false;
		}	
	}
	public ModelAndView confirmarSugerencia(Request req, Response res){
		String sugerenciaNum=req.queryParams("sugerencia");
		if(isNumeric(sugerenciaNum)) {
			Sugerencia sugerencia = sugerencias.get(Integer.parseInt(sugerenciaNum));
			cambiarEstados(sugerencia);
			res.redirect("/calendario");
		}
		res.redirect("/sugerenciasPendientes");
		return null;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////METODOS_QUE_USA_POST_INICIANDO_EN_verSugerencias//////////////
	private boolean esDeEsteEvento(Sugerencia sugerencia,Long idEvento) {
		return sugerencia.getEvento().getId()==idEvento;
	}
	private List<Sugerencia> obtenerSugerencias(Long idEvento,Usuario usuario) {
		List<Sugerencia> sugerencias =  usuario.getSugerencias();
		sugerencias.remove(null);
		return sugerencias.stream().filter(sugerencia->esDeEsteEvento(sugerencia,idEvento)).collect(Collectors.toList());
	}
	public String verSugerencias(Request req, Response res){
		String idEvento = req.cookie("evento");
		Usuario usuario = RepositorioDeUsuarios.getInstance().buscarPorNombre(req.cookie("nombreUsuario"));
		sugerencias = obtenerSugerencias(Long.parseLong(idEvento),usuario);
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("sugerencias", sugerencias);
		ModelAndView modelAndView = new ModelAndView(viewModel, "sugerenciasPendientes.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

}
