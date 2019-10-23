package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Evento;
import domain.frecuenciasDeEventos.FrecuenciaAnual;
import domain.frecuenciasDeEventos.FrecuenciaDeEvento;
import domain.frecuenciasDeEventos.FrecuenciaDiaria;
import domain.frecuenciasDeEventos.FrecuenciaMensual;
import domain.frecuenciasDeEventos.FrecuenciaSemanal;
import domain.frecuenciasDeEventos.FrecuenciaUnicaVez;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;
import tiempo.checkboxes.DiaCheckbox;
import tiempo.checkboxes.Tiempo;

public class EventoController {
	
	static String frecuenciaPosta = null; // Pongo esto porque no se porque se me desaparece la frecuencia casi al final y aun la necesito

	public static String mostrarEventos(Request req, Response res) {
		Map<String, Object> viewModel = new HashMap();
		
		// Primero lo pruebo con un solo evento
		Evento eventoConFrecuenciaUnica = new Evento(new FrecuenciaUnicaVez(2019,2,16),"Sin descripcion");//Fecha "16-02-2019" -> Es decir, un evento finalizado
	
		viewModel.put("descripcion", eventoConFrecuenciaUnica.getDescripcion());
		viewModel.put("frecuencia", eventoConFrecuenciaUnica.getFrecuencia().toString());
		ModelAndView modelAndView = new ModelAndView(viewModel, "mostrarEvento.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
	
	public static String altaDeEvento(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap();
		
		String descripcion = req.queryParams("Descripcion");
		String frecuencia = req.queryParams("Frecuencia");
		
		if(frecuencia != null)
			frecuenciaPosta = frecuencia;
		
		Evento eventoObtenido = null;
		
		if(ingresoFrecuencia()) {
			eventoObtenido = ingresaFecha(req, viewModel, descripcion, eventoObtenido);
		}
		
		viewModel.put("eligieronDesYFrec", frecuenciaPosta!=null);
		
		if(eventoEstaCargado(eventoObtenido)) {
			System.out.println(eventoObtenido);
		}
		
		ModelAndView modelAndView = new ModelAndView(viewModel, "altaDeEvento.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}


	
	
	/*************** Metodos complementarios ***************/
	private static Evento ingresaFecha(Request req, HashMap<String, Object> viewModel, String descripcion,
			Evento eventoObtenido) {
		
		Tiempo fecha = null;
		fecha = obtenerFecha(frecuenciaPosta, req, viewModel);
		FrecuenciaDeEvento frecuenciaDeEvento = fecha.obtenerFrecuencia(req, viewModel);
		
		if(frecuenciaDeEvento != null) {
			eventoObtenido = armarEvento(descripcion, frecuenciaDeEvento);
			vuelveACargarAltaDeEvento();
		}
		return eventoObtenido;
	}


	public static Tiempo obtenerFecha(String frecuencia, Request req, HashMap<String, Object> viewModel) {
		List<Tiempo> elemTiempo = new ArrayList<Tiempo>();
		elemTiempo.add(new DiaCheckbox());
		elemTiempo.stream().filter(tiempo -> tiempo.verificarTiempo(frecuencia));
		elemTiempo.get(0).esPeriodico(viewModel);
		
		return elemTiempo.get(0);
	}
	
	private static void vuelveACargarAltaDeEvento() {
		frecuenciaPosta = null;
	}
	
	public static boolean ingresoFrecuencia() {
		return frecuenciaPosta != null;
	}

	public static Evento armarEvento(String descripcion, FrecuenciaDeEvento frecuencia) {
		return new Evento(frecuencia, descripcion);
	}

	private static boolean eventoEstaCargado(Evento eventoObtenido) {
		return eventoObtenido != null;
	}
	
}
