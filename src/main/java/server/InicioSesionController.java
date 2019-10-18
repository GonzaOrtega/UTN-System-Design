package server;
import java.util.HashMap;

import domain.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class InicioSesionController {
	public static String verificarUsuario(Request req,Response res) {
		String nombreUsuario = req.queryParams("nombreUsuario");
		String alterado = nombreUsuario;
		if(nombreUsuario==null)
			alterado = "";
		else
			alterado = "Sr. "+ alterado;
		Boolean hayKm = nombreUsuario!= null;
		String resultado = "EsteEsResultado";
		HashMap<String,Object> viewModel = new HashMap<String, Object>();

		viewModel.put("nombreUsuario",alterado);
		viewModel.put("hayKm", hayKm);
		viewModel.put("resultado", resultado);
		ModelAndView modelAndView =	new ModelAndView(viewModel,"inicioSesion.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
	
	public static ModelAndView crearSesion(Request req, Response res) {
		RepositorioDeUsuarios repo = RepositorioDeUsuarios.getInstance();
		//agregar tryCatch
		Usuario usuarie = repo.buscarPorNombre(req.params("nombreUsuario"));
		//si no existe, lanzar excepción
		System.out.println("Apreto en LOGIN");
		if (usuarie.validarContrasenia(req.queryParams("pass")) ) {
			res.cookie("uid", usuarie.getId().toString());
			res.redirect("/perfil");
		}
		return null;
	}
	public static String verPerfil(Request req, Response res) {
		HashMap<String,Object> viewModel = new HashMap<String, Object>();
		String nombreUsuario = req.queryParams("nombreUsuario");
		viewModel.put("nombreUsuario",nombreUsuario);
		ModelAndView modelAndView =	new ModelAndView(viewModel,"perfil.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
}