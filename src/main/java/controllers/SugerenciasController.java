package controllers;

import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import domain.Calificacion;
import domain.Evento;
import domain.Guardarropa;
import domain.Prenda;
import domain.PrendaBuilder;
import domain.RepositorioDeUsuarios;
import domain.Sugerencia;
import domain.Sugeridor;
import domain.Usuario;
import domain.apisClima.MockAPI;
import domain.apisClima.OpenWeatherMapAPI;
import domain.apisClima.ProveedorClima;
import domain.enums.Categoria;
import domain.enums.Color;
import domain.enums.Material;
import domain.enums.TipoPrenda;
import domain.enums.TipoSensaciones;
import domain.enums.TipoSugerencias;
import domain.enums.TipoUsuario;
import domain.frecuenciasDeEventos.FrecuenciaDiaria;
import domain.frecuenciasDeEventos.FrecuenciaUnicaVez;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class SugerenciasController extends AbstractPersistenceTest implements WithGlobalEntityManager{
	List<Sugerencia> listaSugerenciaAceptadas;
//	List<Calificacion> listaCalificaciones;
	
	public ModelAndView verSugerenciasAceptadas(Request req, Response res) {
		Map<String, Object> viewModel = new HashMap();
		
		Usuario usuarie = RepositorioDeUsuarios.getInstance().buscarPorNombre(req.cookie("nombreUsuario"));
		
//		Usuario usuario = new Usuario(TipoUsuario.PREMIUM, 100, "pepe", "1234");
		
		
		List<Sugerencia> listaSugerencia = usuarie.getSugerencias();
		withTransaction(()->{
			Sugerencia sugerenciaPosta = generarSugerencia();
			Sugerencia sugerenciaPosta2 = generarSugerencia2();
			sugerenciaPosta.setEstado(TipoSugerencias.ACEPTADA);
			usuarie.getSugerencias().add(sugerenciaPosta2);
			usuarie.getSugerencias().add(sugerenciaPosta);
			
//			EntityManager em = entityManager();
			entityManager().persist(sugerenciaPosta);
			entityManager().persist(sugerenciaPosta2);
		});
		
//		listaCalificaciones = usuarie.getCalificaciones();
		
		Boolean haySugerenciaAceptada = listaSugerencia.stream().anyMatch(sugerencia -> sugerencia.aceptada());
		viewModel.put("haySugerenciaAceptada", haySugerenciaAceptada);
		listaSugerenciaAceptadas = listaSugerencia.stream().filter(sugerencia -> sugerencia.aceptada()).collect(Collectors.toList());
		viewModel.put("sugerencias", listaSugerenciaAceptadas);
		return new ModelAndView(viewModel, "verSugerenciasAceptadas.hbs");
	}
	
	public ModelAndView elegirSugerenciaAceptada(Request req, Response res) {
		String id = req.queryParams("nroSugerencia");
		try {
			listaSugerenciaAceptadas.remove(Integer.parseInt(id));
		}catch(Exception e) {
			res.redirect("/sugerencias/aceptadas");
		}
		res.redirect("/sugerencias/aceptadas/"+ id +"/calificar");
		return null;
	}
	
	public ModelAndView verCalificarSugerencias(Request req, Response res) {
		String id = req.params("id");
		Map<String, Object> viewModel = new HashMap();
		viewModel.put("id", id);
		return new ModelAndView(viewModel, "calificarSugerencias.hbs");
	}
	
	public ModelAndView calificarSugerencias(Request req, Response res) {
		String id = req.params("id");
//		try {
//			EntityManager em = entityManager();
//			em.getTransaction().begin();
			withTransaction(()->{
				Usuario usuarie = RepositorioDeUsuarios.getInstance().buscarPorNombre(req.cookie("nombreUsuario"));
				List<Calificacion> listaCalificaciones = usuarie.getCalificaciones();
				listaCalificaciones.add(this.armarCalificacion("Superior", req));
				listaCalificaciones.add(this.armarCalificacion("Calzado", req));
				listaCalificaciones.add(this.armarCalificacion("Inferior", req));
				listaCalificaciones.add(this.armarCalificacion("Accesorio", req));
			});
//			em.getTransaction().commit();
//		}catch(Exception e) {
//			res.redirect("/sugerencias/aceptadas/"+ id +"/calificar");
//		}
		res.redirect("/perfil");
		return null;
	}
	
	
	
	
	/*************** Metodos complementarios ***************/
	public Calificacion armarCalificacion(String parteCuerpoString, Request req) {
		String sensacionString = req.queryParams(parteCuerpoString);
		Categoria parteCuerpo = Categoria.valueOf(parteCuerpoString);
		TipoSensaciones sensacion = TipoSensaciones.valueOf(sensacionString);
		EntityManager em = entityManager();
		Calificacion calificacion = new Calificacion(parteCuerpo, sensacion);
		em.persist(calificacion);
		return calificacion;
	}
	
	// Hardcodeo esto para probarlo, luego se hace con las sugerencias del usuario que inicia sesion	
	public Sugerencia generarSugerencia() {
		Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.Algodon).conColorPrimario(Color.Rojo).conColorSecundario(Color.Amarillo).crearPrenda();
		Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.Cuero).conColorPrimario(Color.Amarillo).crearPrenda();
		Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.Negro).conTela(Material.Algodon).crearPrenda();
		Prenda jean = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.Jean).conColorPrimario(Color.Azul).crearPrenda();
		Evento eventoConFrecuenciaUnica = new Evento(new FrecuenciaDiaria(1),"Sin descripcion");//Fecha "16-02-2019" -> Es decir, un evento finalizado
		
		Set<Prenda> atuendo = new HashSet<Prenda>();
		atuendo.add(jean);
		atuendo.add(camisaCorta);
		atuendo.add(gorra);
		atuendo.add(zapatos);
		
//		EntityManager em = entityManager();
//		em.getTransaction().begin();
		entityManager().persist(eventoConFrecuenciaUnica);
		entityManager().persist(camisaCorta);
		entityManager().persist(zapatos);
		entityManager().persist(gorra);
		entityManager().persist(jean);
//		em.getTransaction().commit();
		
		return new Sugerencia(atuendo,eventoConFrecuenciaUnica);
	}
	
	public Sugerencia generarSugerencia2() {
		Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.Algodon).conColorPrimario(Color.Rojo).conColorSecundario(Color.Amarillo).crearPrenda();
		Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.Cuero).conColorPrimario(Color.Amarillo).crearPrenda();
		Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.Negro).conTela(Material.Algodon).crearPrenda();
		Prenda jean = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.Jean).conColorPrimario(Color.Azul).crearPrenda();
		Evento eventoConFrecuenciaUnica = new Evento(new FrecuenciaDiaria(1),"Hola!!");//Fecha "16-02-2019" -> Es decir, un evento finalizado
		
		Set<Prenda> atuendo = new HashSet<Prenda>();
		atuendo.add(jean);
		atuendo.add(camisaCorta);
		atuendo.add(gorra);
		atuendo.add(zapatos);
		
//		EntityManager em = entityManager();
//		em.getTransaction().begin();
		entityManager().persist(eventoConFrecuenciaUnica);
		entityManager().persist(camisaCorta);
		entityManager().persist(zapatos);
		entityManager().persist(gorra);
		entityManager().persist(jean);
//		em.getTransaction().commit();
		
		return new Sugerencia(atuendo,eventoConFrecuenciaUnica);
	}
}
