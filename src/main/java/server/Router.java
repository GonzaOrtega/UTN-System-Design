package server;

import controllers.*;

import spark.Spark;
import spark.TemplateEngine;
import spark.template.handlebars.HandlebarsTemplateEngine;
import domain.*;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;



public class Router {
	static Router _instance;
	
	private Router() {
	}
	
	public static Router instance() {
		if(_instance == null) {
			_instance = new Router();
		}
		return _instance;
	}
	
	public void configurar() {
		TemplateEngine engine = new HandlebarsTemplateEngine();
		LoginController loginController = new LoginController();
		UserController userController = new UserController();
		PrendaController prendaContoller = new PrendaController();
		GuardarropasController guardarropasController = new GuardarropasController();
		CalendarioController calendarioController = new CalendarioController();
		SugerenciasPendientesController sugerenciasPendientesController = new SugerenciasPendientesController();
		SugerenciasController sugerenciasController = new SugerenciasController();
		EventoController eventoController = new EventoController();
		
		Spark.get("/", loginController::show, engine);
		Spark.post("/", loginController::login, engine);
		Spark.get("/perfil", userController::showProfile,engine);
		Spark.get("/guardarropas/show", guardarropasController::show,engine);
		Spark.get("/sugerencias/show/aceptadas", sugerenciasController::verSugerenciasAceptadas, engine);
		Spark.post("/sugerencias/show/aceptadas", sugerenciasController::elegirSugerenciaAceptada, engine);
		Spark.get("/sugerencias/calificar/aceptadas", sugerenciasController::verCalificarSugerencias, engine);
		Spark.post("/sugerencias/calificar/aceptadas", sugerenciasController::calificarSugerencias, engine);
		Spark.get("/eventos/alta", eventoController::mostrarAltaDeEvento, engine);
		Spark.post("/eventos/alta", eventoController::elegirDescripcionYFrecuencia, engine);
		Spark.get("/eventos/alta/horarios", eventoController::mostrarOpcionesDeFrecuencia, engine);
		Spark.post("/eventos/alta/horarios", eventoController::completarFrecuencia, engine);
		Spark.post("/eventos/alta/verificacion", eventoController::mostrarMensajeVerificacion, engine);
		Spark.get("/calendario", calendarioController::verCalendario);
		Spark.post("/calendario", calendarioController::verSugerencia);
		Spark.get("/sugerenciasPendientes", sugerenciasPendientesController::verSugerencias);
		Spark.post("/sugerenciasPendientes", sugerenciasPendientesController::confirmarSugerencia);

		Spark.get("/prendas/eleccionGuardarropa", prendaContoller::prueba,engine);
		Spark.post("/prendas/eleccionGuardarropa", prendaContoller::pruebaPost,engine);
		
		Spark.get("/prendas/cargaDatos", prendaContoller::showCargaDatos,engine);
		Spark.post("/prendas/cargaDatos", prendaContoller::saveCargaDatos,engine);
		
		Spark.get("/pru", guardarropasController::pru,engine);
		
		Spark.after((request, response) -> { 
			   PerThreadEntityManagers.getEntityManager(); 
			   PerThreadEntityManagers.closeEntityManager();
			 });
	}
}