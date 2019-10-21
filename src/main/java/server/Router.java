package server;

import spark.Spark;
import spark.TemplateEngine;
import domain.*;
import spark.template.handlebars.HandlebarsTemplateEngine;

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

		Spark.get("/inicioSesion/Usuario",InicioSesionController::verificarUsuario);
		Spark.post("/inicioSesion/Usuario", InicioSesionController::crearSesion);
		Spark.get("/perfil", InicioSesionController::verPerfil);
		Spark.get("/sugerencias/show/aceptadas", SugerenciasController::verSugerenciasAceptadas);
//		Spark.get("/sugerencias/calificar", SugerenciasController::calificarSugerencias);
		Spark.get("/evento/show", EventoController::mostrarEventos);
		Spark.get("/evento/alta", EventoController::altaDeEvento);
	}
}