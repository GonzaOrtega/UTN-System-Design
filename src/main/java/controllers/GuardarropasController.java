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
	public String show(Request req, Response res) {
		Map<String, Object> viewModel = new HashMap<String, Object>();
		//-----------------------------------------------------------------
		
		RepositorioDeUsuarios repo = RepositorioDeUsuarios.getInstance();
		Usuario usuarie = repo.buscarPorNombre(req.cookie("nombreUsuario"));
		List<Guardarropa> guardarropas = usuarie.getGuardarropas().stream().collect(Collectors.toList());
		
		// -----------------------------------------------------------------
		/*
		List<Guardarropa> guardarropas= new ArrayList<Guardarropa>();
		Guardarropa armario = new Guardarropa();
		Guardarropa otroArmario = new Guardarropa();
		hardcodear(armario,otroArmario);
		guardarropas.add(armario);
		*/
		// -----------------------------------------------------------------
		viewModel.put("guardarropas", guardarropas);
		//return new ModelAndView(viewModel,"guardarropa.hbs");
		ModelAndView modelAndView = new ModelAndView(viewModel, "guardarropa.hbs");

		return new HandlebarsTemplateEngine().render(modelAndView);

	}
	
	
	
	
	
	
	
	public void hardcodear(Guardarropa armario, Guardarropa otroArmario) {
		Usuario juan = new Usuario(TipoUsuario.PREMIUM, 0,"juan","123");
		Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.Algodon).conColorPrimario(Color.Rojo).conColorSecundario(Color.Amarillo).crearPrenda();
		Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.Cuero).conColorPrimario(Color.Amarillo).crearPrenda();
		Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.Negro).conTela(Material.Algodon).crearPrenda();
		Prenda camperaGucci = new PrendaBuilder().conTipo(TipoPrenda.Campera).conTela(Material.Algodon).conColorPrimario(Color.Negro).conAbrigo(2).crearPrenda();
		juan.cargarPrenda(armario, camisaCorta);
		juan.cargarPrenda(armario,zapatos);
		juan.cargarPrenda(armario,gorra);
		juan.cargarPrenda(armario,camperaGucci);
	}

}
