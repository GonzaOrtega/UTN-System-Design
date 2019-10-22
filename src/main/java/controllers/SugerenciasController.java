package controllers;

import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import domain.Evento;
import domain.Guardarropa;
import domain.Prenda;
import domain.PrendaBuilder;
import domain.Sugerencia;
import domain.Sugeridor;
import domain.Usuario;
import domain.apisClima.MockAPI;
import domain.apisClima.OpenWeatherMapAPI;
import domain.apisClima.ProveedorClima;
import domain.enums.Color;
import domain.enums.Material;
import domain.enums.TipoPrenda;
import domain.enums.TipoSugerencias;
import domain.enums.TipoUsuario;
import domain.frecuenciasDeEventos.FrecuenciaUnicaVez;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class SugerenciasController {
	public static String verSugerenciasAceptadas(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap();
		
		List<Sugerencia> listaSugerencia = new ArrayList<Sugerencia>();
		Sugerencia sugerenciaPosta = generarSugerencia();
		sugerenciaPosta.setEstado(TipoSugerencias.ACEPTADA);
		listaSugerencia.add(generarSugerencia2());
		listaSugerencia.add(sugerenciaPosta);
		
		
		Boolean haySugerenciaAceptada = listaSugerencia.stream().anyMatch(sugerencia -> sugerencia.aceptada());
		viewModel.put("haySugerenciaAceptada", haySugerenciaAceptada);
		
		List<Sugerencia> listaSugerenciaAceptadas = listaSugerencia.stream().filter(sugerencia -> sugerencia.aceptada()).collect(Collectors.toList());
		
		viewModel.put("sugerencias", listaSugerenciaAceptadas);
		
		ModelAndView modelAndView = new ModelAndView(viewModel, "verSugerenciasAceptadas.hbs");
		
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
	
	
	
	public static String calificarSugerencias(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap();
		
		ModelAndView modelAndView = new ModelAndView(viewModel, "calificarSugerencias.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
	
	
	// Hardcodeo esto para probarlo, luego se hace con las sugerencias del usuario que inicia sesion	
	public static Sugerencia generarSugerencia() {
//		ProveedorClima weatherAPI = new OpenWeatherMapAPI();
//		ProveedorClima APIDeMentiritas = new MockAPI(21,23,false);
//		Usuario juan = new Usuario(TipoUsuario.PREMIUM,0,"juan","123");
//		Guardarropa armario = new Guardarropa();
//		Guardarropa otroArmario = new Guardarropa();
		Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.Algodon).conColorPrimario(Color.Rojo).conColorSecundario(Color.Amarillo).crearPrenda();
		Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.Cuero).conColorPrimario(Color.Amarillo).crearPrenda();
//		Prenda zapatillas = new PrendaBuilder().conTipo(TipoPrenda.Zapatillas).conTela(Material.Cuero).conColorPrimario(Color.AMARILLO).crearPrenda();
		Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.Negro).conTela(Material.Algodon).crearPrenda();
//		Prenda sombrero= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.NEGRO).conTela(Material.Algodon).crearPrenda();
//		Prenda camisaLarga = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaLarga).conColorPrimario(Color.BLANCO).conTela(Material.SATEN).crearPrenda();
//		Prenda camisaDeLara = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaLarga).conColorPrimario(Color.BLANCO).conTela(Material.SATEN).crearPrenda();
//		Prenda ojotas = new PrendaBuilder().conTipo(TipoPrenda.Ojotas).conTela(Material.CAUCHO).conColorPrimario(Color.NEGRO).crearPrenda();
		Prenda jean = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.Jean).conColorPrimario(Color.Azul).crearPrenda();
//		Prenda pantalon = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.Jean).conColorPrimario(Color.BLANCO).crearPrenda();
		Evento eventoConFrecuenciaUnica = new Evento(new FrecuenciaUnicaVez(2019,2,16),"Sin descripcion");//Fecha "16-02-2019" -> Es decir, un evento finalizado
		
		Set<Prenda> atuendo = new HashSet<Prenda>();
		atuendo.add(jean);
		atuendo.add(camisaCorta);
		atuendo.add(gorra);
		atuendo.add(zapatos);
		return new Sugerencia(atuendo,eventoConFrecuenciaUnica);
	}
	
	public static Sugerencia generarSugerencia2() {
//		ProveedorClima weatherAPI = new OpenWeatherMapAPI();
//		ProveedorClima APIDeMentiritas = new MockAPI(21,23,false);
//		Usuario juan = new Usuario(TipoUsuario.PREMIUM,0,"juan","123");
//		Guardarropa armario = new Guardarropa();
//		Guardarropa otroArmario = new Guardarropa();
		Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.Algodon).conColorPrimario(Color.Rojo).conColorSecundario(Color.Amarillo).crearPrenda();
		Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.Cuero).conColorPrimario(Color.Amarillo).crearPrenda();
//		Prenda zapatillas = new PrendaBuilder().conTipo(TipoPrenda.Zapatillas).conTela(Material.Cuero).conColorPrimario(Color.AMARILLO).crearPrenda();
		Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.Negro).conTela(Material.Algodon).crearPrenda();
//		Prenda sombrero= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.NEGRO).conTela(Material.Algodon).crearPrenda();
//		Prenda camisaLarga = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaLarga).conColorPrimario(Color.BLANCO).conTela(Material.SATEN).crearPrenda();
//		Prenda camisaDeLara = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaLarga).conColorPrimario(Color.BLANCO).conTela(Material.SATEN).crearPrenda();
//		Prenda ojotas = new PrendaBuilder().conTipo(TipoPrenda.Ojotas).conTela(Material.CAUCHO).conColorPrimario(Color.NEGRO).crearPrenda();
		Prenda jean = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.Jean).conColorPrimario(Color.Azul).crearPrenda();
//		Prenda pantalon = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.Jean).conColorPrimario(Color.BLANCO).crearPrenda();
		Evento eventoConFrecuenciaUnica = new Evento(new FrecuenciaUnicaVez(2019,2,16),"Hola!!");//Fecha "16-02-2019" -> Es decir, un evento finalizado
		
		Set<Prenda> atuendo = new HashSet<Prenda>();
		atuendo.add(jean);
		atuendo.add(camisaCorta);
		atuendo.add(gorra);
		atuendo.add(zapatos);
		return new Sugerencia(atuendo,eventoConFrecuenciaUnica);
	}
	
}
