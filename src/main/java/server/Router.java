package server;

import spark.Spark;
import domain.*;

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
	Spark.get("/conversor/millas",InicioSesionController::verificarUsuario );
	}
}
