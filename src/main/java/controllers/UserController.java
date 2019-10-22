package controllers;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class UserController {
	public ModelAndView showProfile(Request req, Response res) {
		Map<String,Object> viewModel = new HashMap<String, Object>();
		String username = req.cookie("nombreUsuario");
		viewModel.put("nombreUsuario",username);
		return new ModelAndView(viewModel,"perfil.hbs");
	}
}
