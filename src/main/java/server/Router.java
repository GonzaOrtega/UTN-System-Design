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
		Calendario2Controller cal2Controller = new Calendario2Controller();
		
		Spark.get("/", loginController::show, engine);
		Spark.post("/", loginController::login, engine);
		Spark.get("/perfil", userController::showProfile,engine);
		Spark.get("/guardarropas/show", guardarropasController::show,engine);
		Spark.get("/sugerencias/aceptadas", sugerenciasController::verSugerenciasAceptadas, engine);
		Spark.post("/sugerencias/aceptadas", sugerenciasController::elegirSugerenciaAceptada, engine);
		Spark.get("/sugerencias/aceptadas/calificar", sugerenciasController::verCalificarSugerencias, engine);
		Spark.post("/sugerencias/aceptadas/calificar", sugerenciasController::calificarSugerencias, engine);
		Spark.get("/eventos/nuevo", eventoController::mostrarAltaDeEvento, engine);
		Spark.post("/eventos/nuevo", eventoController::elegirDescripcionYFrecuencia, engine);
		Spark.get("/eventos/nuevo/horarios", eventoController::mostrarOpcionesDeFrecuencia, engine);
		Spark.post("/eventos/nuevo/horarios", eventoController::completarFrecuencia, engine);
		Spark.get("/eventos/nuevo/verificacion", eventoController::mostrarMensajeVerificacion, engine);
		Spark.post("/eventos/nuevo/verificacion", eventoController::volverAInicio, engine);
		Spark.get("/calendario", calendarioController::verCalendario);
		Spark.post("/calendario", calendarioController::verSugerencia);
		Spark.get("/sugerenciasPendientes", sugerenciasPendientesController::verSugerencias);
		Spark.post("/sugerenciasPendientes", sugerenciasPendientesController::confirmarSugerencia);

		Spark.get("/prendas/eleccionGuardarropa", prendaContoller::prueba,engine);
		Spark.post("/prendas/eleccionGuardarropa", prendaContoller::pruebaPost,engine);
		
		Spark.get("/prendas/cargaDatos", prendaContoller::showCargaDatos,engine);
		Spark.post("/prendas/cargaDatos", prendaContoller::saveCargaDatos,engine);
		
		Spark.get("/guardarropas", guardarropasController::pru,engine);
		
		Spark.get("/calendar",cal2Controller::test,engine);
		Spark.get("/calendar/busqueda",cal2Controller::busquedaPorFecha,engine);
		
		Spark.after((request, response) -> { 
			   PerThreadEntityManagers.getEntityManager(); 
			   PerThreadEntityManagers.closeEntityManager();
			 });
	}
}