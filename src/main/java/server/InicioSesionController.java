package server;
import java.util.HashMap;

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
			alterado = "NO HAY NADA";
		else
			alterado = "Sr. "+ alterado;
		Boolean hayKm = nombreUsuario!= null;
		String resultado = "EsteEsResultado";
		HashMap<String,Object> viewModel = new HashMap();

		viewModel.put("nombreUsuario",alterado);
		viewModel.put("hayKm", hayKm);
		viewModel.put("resultado", resultado);
		ModelAndView modelAndView =	new ModelAndView(viewModel,"inicioSesion.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
}
