package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

public class EventoController {
	
	public static String mostrarEventos(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap();
		
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
		
		// Hago que me muestre la descripcion por pantalla, para probar nada mas
		viewModel.put("descripcionIngresada", descripcion);
		String descripcionObtenida = descripcion;
		viewModel.put("descripcionObtenida", descripcionObtenida);
		
		boolean eligieronDesYFrec = false; // Esto sirve tanto para cuando se inicia la pagina como para si la persona no eligio frecuencia
		
		if(frecuencia != null) { // Si no veo de tirar una exception en algun lado
			eligieronDesYFrec = true;
			Evento eventoObtenido = armarEvento(descripcion, obtenerFrecuencia(frecuencia, viewModel));
		}
		
		viewModel.put("eligieronDesYFrec", eligieronDesYFrec);
		
		ModelAndView modelAndView = new ModelAndView(viewModel, "altaDeEvento.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

	
	// Metodos complementarios
	public static Evento armarEvento(String descripcion, FrecuenciaDeEvento frecuencia) {
		return new Evento(frecuencia, descripcion);
	}
	
	private static FrecuenciaDeEvento obtenerFrecuencia(String frecuencia, HashMap<String, Object> viewModel) {
		// Falta arreglar los elementos hardcodeados
		boolean esFrecuencia = true;
		switch(frecuencia) {
		case "Anual":
			viewModel.put("esAnual", esFrecuencia);
			return new FrecuenciaAnual(1, 1);
		case "Diaria":
			viewModel.put("esDiaria", esFrecuencia);
			return new FrecuenciaDiaria(1);
		case "Mensual":
			viewModel.put("esMensual", esFrecuencia);
			return new FrecuenciaMensual(1);
		case "Semanal":
			viewModel.put("esSemanal", esFrecuencia);
			return new FrecuenciaSemanal(1);
		case "UnicaVez":
			viewModel.put("esUnicaVez", esFrecuencia);
			return new FrecuenciaUnicaVez(1, 1, 1);
		default:
			return null; // Esto no va a pasar nunca ya que esta seteado que para esta altura ya haya una frecuencia (valida por checkpoints)
		}
	}
	
	public static FrecuenciaDeEvento configurarFrecuencia(FrecuenciaDeEvento frecuencia) {
		
		
		
		return frecuencia;
	}
}
