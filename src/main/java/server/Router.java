package server;

import spark.Spark;
import spark.TemplateEngine;
<<<<<<< HEAD
=======
import domain.*;
import server.controller.CalendarioController;
>>>>>>> e9c63c47adc762375e4a97ba73f9805138e12544
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
		
		Spark.get("/", loginController::show, engine);
		Spark.post("/", loginController::login, engine);
		Spark.get("/perfil", userController::showProfile,engine);
		Spark.get("/sugerencias/show/aceptadas", SugerenciasController::verSugerenciasAceptadas);
//		Spark.get("/sugerencias/calificar", SugerenciasController::calificarSugerencias);
		Spark.get("/evento/show", EventoController::mostrarEventos);
		Spark.get("/evento/alta", EventoController::altaDeEvento);
		Spark.get("/calendario", CalendarioController::verCalendario);
	}
}