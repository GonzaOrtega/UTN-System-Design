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
		String km = req.queryParams("km");
		Double kmNumero;
		if(km==null)
			kmNumero = 0.0;
		else
			kmNumero = Double.parseDouble(km);
		Boolean hayKm = km!= null;
		Double millas = 2*(kmNumero);
		HashMap<String,Object> viewModel = new HashMap();

		viewModel.put("km",kmNumero);
		viewModel.put("hayKm", hayKm);
		viewModel.put("millas", millas);
		ModelAndView modelAndView =	new ModelAndView(viewModel,"inicioSesion.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
}
