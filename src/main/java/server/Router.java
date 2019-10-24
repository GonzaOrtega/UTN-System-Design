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
		Spark.get("/", loginController::show, engine);
		Spark.post("/", loginController::login, engine);
		Spark.get("/perfil", userController::showProfile,engine);
		Spark.get("/guardarropas/show", guardarropasController::show,engine);
		Spark.get("/sugerencias/show/aceptadas", SugerenciasController::verSugerenciasAceptadas);
		Spark.get("/evento/show", EventoController::mostrarEventos);
		Spark.get("/evento/alta", EventoController::altaDeEvento);
		Spark.get("/calendario", CalendarioController::verCalendario);
		Spark.get("/sugerencias", SugerenciasDisponiblesController::verSugerencias);
		Spark.get("/prendas/step-1", prendaContoller::showstep1,engine);
		Spark.post("/prendas/step-1", prendaContoller::load_step1,engine);		 
		Spark.get("/prendas/step-2", prendaContoller::showstep2,engine);	
		Spark.post("/prendas/step-2", prendaContoller::load_step2,engine);		 
		Spark.get("/prendas/step-3", prendaContoller::showstep3,engine);	
		Spark.post("/prendas/step-3", prendaContoller::load_step3,engine);	
		Spark.get("/prendas/step-4", prendaContoller::showstep4,engine);
		Spark.get("/prendas/prueba", prendaContoller::prueba,engine);
		Spark.post("/prendas/prueba", prendaContoller::pruebaPost,engine);
	}
}