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
	
	Tiempo fecha = null;
	
	public static String mostrarEventos(Request req, Response res) {
		Map<String, Object> viewModel = new HashMap();
		
		// Primero lo pruebo con un solo evento
		Evento eventoConFrecuenciaUnica = new Evento(new FrecuenciaUnicaVez(2019,2,16),"Sin descripcion");//Fecha "16-02-2019" -> Es decir, un evento finalizado
		
		viewModel.put("descripcion", eventoConFrecuenciaUnica.getDescripcion());
		viewModel.put("frecuencia", eventoConFrecuenciaUnica.getFrecuencia().toString());
		ModelAndView modelAndView = new ModelAndView(viewModel, "mostrarEvento.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

	public ModelAndView mostrarAltaDeEvento(Request req, Response res) {
		return new ModelAndView(null, "altaDeEvento.hbs");
	}

	public ModelAndView elegirDescripcionYFrecuencia(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap();
		String descripcion = req.queryParams("Descripcion");
		String frecuencia = req.queryParams("Frecuencia");
		fecha = this.obtenerFecha(frecuencia);
		res.cookie("Descripcion", descripcion);
		return this.mostrarOpcionesDeFrecuencia(req, res);
	}
	
	public ModelAndView mostrarOpcionesDeFrecuencia(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap();
		viewModel.put(fecha.esPeriodico(), true);
		viewModel.put("fechaIncorrecta", false);
		return new ModelAndView(viewModel, "elegirHorarios.hbs");
	}
	
	public ModelAndView completarFrecuencia(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap();
		viewModel.put("fechaIncorrecta", false);
		String descripcion = req.cookie("Descripcion");
		if(fecha.datosIngresadosCorrectamente(req)) {
			FrecuenciaDeEvento frecuenciaDeEvento = fecha.obtenerFrecuencia();
			Evento eventoObtenido = this.armarEvento(descripcion, frecuenciaDeEvento);
			this.cargarEvento(eventoObtenido, req);
		}else {
			viewModel.put("fechaIncorrecta", true);
			return new ModelAndView(viewModel, "elegirHorarios.hbs");
		}
		res.redirect("/perfil");
		return null;
	}
	
	/*************** Metodos complementarios ***************/

	public Tiempo obtenerFecha(String frecuencia) {
		List<Tiempo> elemTiempo = new ArrayList<Tiempo>();
		elemTiempo.add(new DiaCheckbox());
		elemTiempo.add(new AnioCheckbox());
		elemTiempo.add(new SemanaCheckbox());
		elemTiempo.add(new MesCheckbox());
		elemTiempo.add(new UnicaVezCheckbox());
		elemTiempo = elemTiempo.stream().filter(tiempo -> tiempo.verificarTiempo(frecuencia)).collect(Collectors.toList());
		
		return elemTiempo.get(0);
	}
	
	public void cargarEvento(Evento evento, Request req) {
		EventoController eventoController = new EventoController();
		
		// Para la hora de usarlo posta
//		RepositorioDeUsuarios repo = RepositorioDeUsuarios.getInstance();
//		Usuario usuarie = repo.buscarPorNombre(req.cookie("nombreUsuario"));
//		usuarie.agendarEvento(evento);
		
		
		EntityManager em = entityManager();
		em.getTransaction().begin();
		em.persist(evento);
		em.getTransaction().commit();
	}
	
	public Evento armarEvento(String descripcion, FrecuenciaDeEvento frecuencia) {
		return new Evento(frecuencia, descripcion);
	}
}
