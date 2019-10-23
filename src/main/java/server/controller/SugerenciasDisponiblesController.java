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
	private List<Sugerencia> obtenerSugerencias(Long idEvento) {
		Set<Evento> eventos =RepositorioDeUsuarios.getInstance().eventos();
		Evento evento =eventos.stream().filter(even->even.getId() == idEvento).collect(Collectors.toList()).get(0);
		Set<Sugerencia> sugerencias = RepositorioDeUsuarios.getInstance().usuarios().stream()
			.flatMap(usuario->usuario.getSugerencias().stream()).collect(Collectors.toSet());
		return sugerencias.stream().filter(sugerencia->sugerencia.getEvento()==evento).collect(Collectors.toList());
	}
	public String verSugerencias(Request req, Response res){
		String idEvento = req.cookie("evento");
		sugerencias = obtenerSugerencias(Long.parseLong(idEvento));
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
