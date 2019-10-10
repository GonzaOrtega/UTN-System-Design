package server;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class InicioSesionController {
	public static boolean verificarUsuario(Request req,Response res) {
		return true;
	}
}
