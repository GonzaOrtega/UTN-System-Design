package server.controller;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import domain.Evento;
import domain.Prenda;
import domain.PrendaBuilder;
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
	public static String verSugerencias(Request req, Response res){
		//String evento= req.queryParams("evento");
		Evento evento = new Evento(new FrecuenciaUnicaVez(2019,5,24),"Sin descripcion");
		Usuario usuario = new Usuario(TipoUsuario.PREMIUM,0,"ana","123");
		Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.Algodon).conColorPrimario(Color.Rojo).conColorSecundario(Color.Amarillo).crearPrenda();
		Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.Cuero).conColorPrimario(Color.Amarillo).crearPrenda();
		Set<Prenda> atuendo= new HashSet<Prenda>();
		Set<Prenda> atuendo2= new HashSet<Prenda>();
		atuendo.add(camisaCorta);
		atuendo.add(zapatos);
		atuendo2.add(camisaCorta);
		Sugerencia sugerenciaLoca = new Sugerencia(atuendo,evento);
		Sugerencia sugerenciaLoca2 = new Sugerencia(atuendo2,evento);
		usuario.agregarSugerencia(sugerenciaLoca);
		usuario.agregarSugerencia(sugerenciaLoca2);
		List<Sugerencia> sugerencias= usuario.getSugerencias();
		
		boolean haySugerencias;
		boolean haySugerenciasAceptadas;
		HashMap<String, Object> viewModel = new HashMap<>();
		sugerencias = sugerencias.stream().filter(sugerencia->sugerencia.getEvento() == evento)
				.collect(Collectors.toList());
		haySugerencias = !sugerencias.isEmpty();
		haySugerenciasAceptadas = sugerencias.stream().anyMatch(sugerencia->sugerencia.getEstado()==TipoSugerencias.ACEPTADA);
		viewModel.put("sugerencias", sugerencias);
		viewModel.put("haySugerencias",haySugerencias);
		viewModel.put("haySugerenciasAceptadas",haySugerenciasAceptadas);
		ModelAndView modelAndView = new ModelAndView(viewModel, "sugerenciasPendientes.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

}
