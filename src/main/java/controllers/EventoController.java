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
		
		if(frecuenciaPosta != null) { // Si no veo de tirar una exception en algun lado
			Tiempo fecha = null;
			fecha = organizacion(frecuenciaPosta, req, viewModel);
			FrecuenciaDeEvento frecuenciaDeEvento = fecha.obtenerFrecuencia(req, viewModel);
			if(frecuenciaDeEvento != null) {
				eventoObtenido = armarEvento(descripcion, frecuenciaDeEvento);
				frecuenciaPosta = null;
			}
		}
		// Para cuando se ingresen todos lo valores correctamente a esta altura ya esta el evento cargado. Vamos a probarlo
		viewModel.put("eligieronDesYFrec", frecuenciaPosta!=null);
		System.out.println(eventoObtenido);
		
		// Para usar el evento ya cargado hay que hacer if(eventoObtenido != null) y usarlo
		// Se que esta quedando muy desprolijo pero ya lo arreglo
		// #DeudaTecnica
		
		ModelAndView modelAndView = new ModelAndView(viewModel, "altaDeEvento.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

	public static Tiempo organizacion(String frecuencia, Request req, HashMap<String, Object> viewModel) {
		List<Tiempo> elemTiempo = new ArrayList<Tiempo>();
		elemTiempo.add(new DiaCheckbox());
		elemTiempo.stream().filter(tiempo -> tiempo.verificarTiempo(frecuencia));
		elemTiempo.get(0).esPeriodico(viewModel);
		
		return elemTiempo.get(0);
	}
	
	// Metodos complementarios
	public static Evento armarEvento(String descripcion, FrecuenciaDeEvento frecuencia) {
		return new Evento(frecuencia, descripcion);
	}
	
}
