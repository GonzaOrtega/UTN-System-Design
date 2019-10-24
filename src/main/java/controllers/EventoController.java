package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import domain.Evento;
import domain.RepositorioDeUsuarios;
import domain.Usuario;
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
import tiempo.checkboxes.AnioCheckbox;
import tiempo.checkboxes.DiaCheckbox;
import tiempo.checkboxes.MesCheckbox;
import tiempo.checkboxes.SemanaCheckbox;
import tiempo.checkboxes.Tiempo;
import tiempo.checkboxes.UnicaVezCheckbox;

public class EventoController  extends AbstractPersistenceTest implements WithGlobalEntityManager{
	
	static String frecuenciaIngresada = null; // Pongo esto porque no se porque se me desaparece la frecuencia casi al final y aun la necesito
	static String descripcionIngresada = null;
	
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
		RepositorioDeUsuarios repo = RepositorioDeUsuarios.getInstance();
		Usuario usuarie = repo.buscarPorNombre(req.cookie("nombreUsuario"));
		
		obtenerFrecuenciaYDescripcion(req);
		
		Evento eventoObtenido = null;
		
		if(ingresoDescripcionYFrecuencia()) {
			eventoObtenido = ingresaFecha(req, viewModel, eventoObtenido);
		}
		
		viewModel.put("eligieronDesYFrec", frecuenciaIngresada != null);
		
		if(eventoEstaCargado(eventoObtenido)) {
			EventoController eventoController = new EventoController();
			usuarie.agendarEvento(eventoObtenido);
			eventoController.cargarEvento(eventoObtenido);
		}
		ModelAndView modelAndView = new ModelAndView(viewModel, "altaDeEvento.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}



	
	
	/*************** Metodos complementarios ***************/
	private static Evento ingresaFecha(Request req, HashMap<String, Object> viewModel, Evento eventoObtenido) {
		
		Tiempo fecha = null;
		fecha = obtenerFecha(frecuenciaIngresada, req, viewModel);
		FrecuenciaDeEvento frecuenciaDeEvento = fecha.obtenerFrecuencia(req, viewModel);
		
		if(frecuenciaDeEvento != null) {
			eventoObtenido = armarEvento(descripcionIngresada, frecuenciaDeEvento);
			vuelveACargarAltaDeEvento();
		}
		return eventoObtenido;
	}


	public static Tiempo obtenerFecha(String frecuencia, Request req, HashMap<String, Object> viewModel) {
		List<Tiempo> elemTiempo = new ArrayList<Tiempo>();
		elemTiempo.add(new DiaCheckbox());
		elemTiempo.add(new AnioCheckbox());
		elemTiempo.add(new SemanaCheckbox());
		elemTiempo.add(new MesCheckbox());
		elemTiempo.add(new UnicaVezCheckbox());
		elemTiempo = elemTiempo.stream().filter(tiempo -> tiempo.verificarTiempo(frecuencia)).collect(Collectors.toList());
		elemTiempo.get(0).esPeriodico(viewModel);
		
		return elemTiempo.get(0);
	}
	
	public void cargarEvento(Evento evento) {
		EntityManager em = entityManager();
		em.getTransaction().begin();
		em.persist(evento);
		em.getTransaction().commit();
	}
	
	private static void vuelveACargarAltaDeEvento() {
		frecuenciaIngresada = null;
	}
	
	public static boolean ingresoDescripcionYFrecuencia() {
		return frecuenciaIngresada != null && descripcionIngresada != null;
	}

	public static Evento armarEvento(String descripcion, FrecuenciaDeEvento frecuencia) {
		return new Evento(frecuencia, descripcion);
	}

	private static boolean eventoEstaCargado(Evento eventoObtenido) {
		return eventoObtenido != null;
	}
	
	private static void obtenerFrecuenciaYDescripcion(Request req) {
		String descripcion = req.queryParams("Descripcion");
		String frecuencia = req.queryParams("Frecuencia");
		if(frecuencia != null) {
			frecuenciaIngresada = frecuencia;
			descripcionIngresada = descripcion;
		}
	}
}
