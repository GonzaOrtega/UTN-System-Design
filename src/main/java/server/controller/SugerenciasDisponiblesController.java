package server.controller;


import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import domain.RepositorioDeUsuarios;
import domain.Usuario;
import domain.enums.TipoSugerencias;
import domain.Sugerencia;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class SugerenciasDisponiblesController implements WithGlobalEntityManager{
	private List<Sugerencia> sugerencias=null;
	public ModelAndView confirmarSugerencia(Request req, Response res){
		String sugerenciaNum=req.queryParams("sugerencia");
		if(isNumeric(sugerenciaNum)) {
			Sugerencia sugerencia = sugerencias.get(Integer.parseInt(sugerenciaNum));
			entityManager().getTransaction().begin();
				sugerencia.setEstado(TipoSugerencias.ACEPTADA);
				sugerencias.remove(sugerencia);
				sugerencias.forEach(sug->sug.setEstado(TipoSugerencias.RECHAZADA));
			entityManager().getTransaction().commit();
			res.redirect("/calendario");
			
		}
		res.redirect("/sugerencias");
		return new ModelAndView(null, "sugerenciasPendientes.hbs");
	}
	public static boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}
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
		System.out.println(idEvento);
		Usuario usuario = RepositorioDeUsuarios.getInstance().buscarPorNombre(req.cookie("nombreUsuario"));
		sugerencias = obtenerSugerencias(Long.parseLong(idEvento),usuario);
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("sugerencias", sugerencias);
		viewModel.put("sugerencia", "a");
		ModelAndView modelAndView = new ModelAndView(viewModel, "sugerenciasPendientes.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

}
