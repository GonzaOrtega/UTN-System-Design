package controllers;
import java.util.HashMap;
import java.util.Map;

import domain.Evento;
import domain.PrendaBuilder;
import domain.enums.Categoria;
import domain.enums.Color;
import domain.enums.Material;
import domain.enums.TipoPrenda;
import domain.frecuenciasDeEventos.FrecuenciaUnicaVez;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class PrendaController {
	
	PrendaBuilder builder = new PrendaBuilder();
	
	public  ModelAndView showstep1(Request req, Response res) {
		return new ModelAndView(null, "step1.hbs");
	}
	
	public  ModelAndView showstep2(Request req, Response res) {
		return new ModelAndView(null, "step2.hbs");
	}
	
	public  ModelAndView load_step1(Request req, Response res) {
		String tipo = req.queryParams("tipo");
		String colorP = req.queryParams("colorPrimario");
		String colorS = req.queryParams("colorSecundario");
		TipoPrenda tipo_de_prenda = TipoPrenda.valueOf(tipo);
		Color colorPrimario = Color.valueOf(colorP);
		Color colorSecundario = Color.valueOf(colorS);
		builder.conTipo(tipo_de_prenda);
		builder.conColorPrimario(colorPrimario);
		builder.conColorSecundario(colorSecundario);
		res.redirect("/prendas/step-2");
		return null;
	}
	public  ModelAndView load(Request req, Response res) {
		return new ModelAndView(null, "step2.hbs");
	}
	
}
