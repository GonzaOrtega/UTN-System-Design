package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.*;
import domain.enums.Color;
import domain.enums.Material;
import domain.enums.TipoPrenda;
import domain.enums.TipoUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class GuardarropasController {
	/*
	public ModelAndView show(Request req, Response res) {
		Map<String, Object> viewModel = new HashMap<String, Object>();		
		RepositorioDeUsuarios repo = RepositorioDeUsuarios.getInstance();
		Usuario usuarie = repo.buscarPorNombre(req.cookie("nombreUsuario"));
		List<Guardarropa> guardarropas = usuarie.getGuardarropas().stream().collect(Collectors.toList());
		Boolean hayGuardarropas = !guardarropas.isEmpty();
		viewModel.put("hayGuardarropas", hayGuardarropas);
		viewModel.put("guardarropas", guardarropas);
		return new ModelAndView(viewModel,"guardarropa.hbs");
	}*/
	
	public ModelAndView show(Request req, Response res) {
		Map<String,Object> viewModel = new HashMap<String, Object>();
		RepositorioDeUsuarios repo = RepositorioDeUsuarios.getInstance();
		Usuario usuarie = repo.buscarPorNombre(req.cookie("nombreUsuario"));
		List<Guardarropa> guardarropas = usuarie.getGuardarropas().stream().collect(Collectors.toList());
		viewModel.put("guardarropas", guardarropas);
	return new ModelAndView(viewModel,"guardarropa2.hbs");

	}
}
