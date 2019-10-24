package server.controller;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import domain.Evento;
import domain.Prenda;
import domain.PrendaBuilder;
import domain.RepositorioDeUsuarios;
import domain.Usuario;
import domain.enums.Color;
import domain.enums.Material;
import domain.enums.TipoPrenda;
import domain.enums.TipoSugerencias;
import domain.enums.TipoUsuario;
import domain.frecuenciasDeEventos.FrecuenciaUnicaVez;
import domain.Sugerencia;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class SugerenciasDisponiblesController {
	private List<Sugerencia> sugerencias=null;
	public static ModelAndView confirmarSugerencia(Request req, Response res){
		String sugerenciaNum=req.queryParams("sugerencia");
		if(isNumeric(sugerenciaNum)) {
			res.redirect("/calendario");
		}
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
		return usuario.getSugerencias().stream().filter(sugerencia->esDeEsteEvento(sugerencia,idEvento)).collect(Collectors.toList());
	}
	public String verSugerencias(Request req, Response res){
		String idEvento = req.cookie("evento");
		Usuario usuario = RepositorioDeUsuarios.getInstance().buscarPorNombre(req.cookie("nombreUsuario"));
		sugerencias = obtenerSugerencias(Long.parseLong(idEvento),usuario);
		boolean haySugerencias;
		boolean haySugerenciasAceptadas;
		HashMap<String, Object> viewModel = new HashMap<>();
		haySugerencias = !sugerencias.isEmpty();
		haySugerenciasAceptadas = sugerencias.stream().anyMatch(sugerencia->sugerencia.getEstado()==TipoSugerencias.ACEPTADA);
		viewModel.put("sugerencias", sugerencias);
		viewModel.put("haySugerencias",haySugerencias);
		viewModel.put("haySugerenciasAceptadas",haySugerenciasAceptadas);
		ModelAndView modelAndView = new ModelAndView(viewModel, "sugerenciasPendientes.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

}
