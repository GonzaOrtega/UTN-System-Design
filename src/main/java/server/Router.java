package server;

import spark.Spark;
import spark.TemplateEngine;
import domain.*;
import server.controller.CalendarioController;
import server.controller.SugerenciasDisponiblesController;
import spark.template.handlebars.HandlebarsTemplateEngine;
import controllers.*;

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
		SugerenciasDisponiblesController sugerenciasDisponiblesController = new SugerenciasDisponiblesController();
		SugerenciasController sugerenciasController = new SugerenciasController();
		
		Spark.get("/", loginController::show, engine);
		Spark.post("/", loginController::login, engine);
		Spark.get("/perfil", userController::showProfile,engine);
		Spark.get("/guardarropas/show", guardarropasController::show,engine);
		Spark.get("/sugerencias/show/aceptadas", sugerenciasController::verSugerenciasAceptadas, engine);
		Spark.post("/sugerencias/show/aceptadas", sugerenciasController::elegirSugerenciaAceptada, engine);
		Spark.get("/sugerencias/calificar/aceptadas", sugerenciasController::verCalificarSugerencias, engine);
		Spark.post("/sugerencias/calificar/aceptadas", sugerenciasController::calificarSugerencias, engine);
		Spark.get("/evento/show", EventoController::mostrarEventos);
		Spark.get("/evento/alta", EventoController::altaDeEvento);
		Spark.get("/calendario", calendarioController::verCalendario);
		Spark.post("/calendario", calendarioController::verSugerencia);
		Spark.get("/sugerencias", sugerenciasDisponiblesController::verSugerencias);
		Spark.post("/sugerencias", sugerenciasDisponiblesController::confirmarSugerencia);
		Spark.get("/prendas/step-1", prendaContoller::showstep1,engine);
		Spark.post("/prendas/step-1", prendaContoller::load_step1,engine);		 
		Spark.get("/prendas/step-2", prendaContoller::showstep2,engine);	
		Spark.post("/prendas/step-2", prendaContoller::load_step2,engine);		 
		Spark.get("/prendas/step-3", prendaContoller::showstep3,engine);	
		Spark.post("/prendas/step-3", prendaContoller::load_step3,engine);	
		Spark.get("/prendas/step-4", prendaContoller::showstep4,engine);
//		Spark.get("/prendas/prueba", prendaContoller::prueba,engine);
//		Spark.post("/prendas/prueba", prendaContoller::pruebaPost,engine);
	}
}