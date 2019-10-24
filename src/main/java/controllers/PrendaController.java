package controllers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import domain.Guardarropa;
import domain.Prenda;
import domain.PrendaBuilder;
import domain.RepositorioDeUsuarios;
import domain.Usuario;
import domain.enums.Color;
import domain.enums.Material;
import domain.enums.TipoPrenda;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class PrendaController implements WithGlobalEntityManager, TransactionalOps{
	//EntityManager em = entityManager();
	PrendaBuilder builder = new PrendaBuilder();
	
	public  ModelAndView showstep1(Request req, Response res) {
		return new ModelAndView(null, "step1.hbs");
	}
	
	public  ModelAndView showstep2(Request req, Response res) {
		Map<String,Object> viewModel = new HashMap<String, Object>();
		String tipo = req.cookie("tipoPrenda");
		String colorP = req.cookie("colorPrimario");
		String colorS = req.cookie("colorSecundario");
		viewModel.put("tipoPrenda",tipo);
		viewModel.put("colorPrimario",colorP);
		viewModel.put("colorSecundario",colorS);
		return new ModelAndView(viewModel, "step2.hbs");
	}
	
	public  ModelAndView showstep3(Request req, Response res) {
		Map<String,Object> viewModel = new HashMap<String, Object>();
		String tipo = req.cookie("tipoPrenda");
		String colorP = req.cookie("colorPrimario");
		String colorS = req.cookie("colorSecundario");
		String material = req.cookie("material");
		String nivel_abrigo = req.cookie("nivelDeAbrigo");
		viewModel.put("tipoPrenda",tipo);
		viewModel.put("colorPrimario",colorP);
		viewModel.put("colorSecundario",colorS);
		viewModel.put("tela",material);
		viewModel.put("nivelAbrigo",nivel_abrigo);
		RepositorioDeUsuarios repo = RepositorioDeUsuarios.getInstance();
		Usuario usuarie = repo.buscarPorNombre(req.cookie("nombreUsuario"));
		List<Guardarropa> guardarropas = usuarie.getGuardarropas().stream().collect(Collectors.toList());
		viewModel.put("guardarropas", guardarropas);
		return new ModelAndView(viewModel, "step3.hbs");
	}
	
	public  ModelAndView showstep4(Request req, Response res) {
		return new ModelAndView(null, "step4.hbs");
	}
	
	public  ModelAndView load_step1(Request req, Response res) {
		String tipo = req.queryParams("tipoPrenda");
		String colorP = req.queryParams("colorPrimario");
		String colorS = req.queryParams("colorSecundario");

		try {
		TipoPrenda tipo_de_prenda = TipoPrenda.valueOf(tipo);
		builder.conTipo(tipo_de_prenda);
		Color colorPrimario = Color.valueOf(colorP);
		builder.conColorPrimario(colorPrimario);
			if(!colorS.equals("SinColor")){
				Color colorSecundario = Color.valueOf(colorS);	
				builder.conColorSecundario(colorSecundario);
			}
			else {
				builder.conColorSecundario(null);
			}
		res.cookie("tipoPrenda",tipo);
		res.cookie("colorPrimario", colorP);
		res.cookie("colorSecundario", colorS);
		
		}
		catch(Exception e) {
				System.out.println("Eror->" + e);
				res.redirect("/prendas/step-1");
		}
		res.redirect("/prendas/step-2");
		return null;
	}
	
	public  ModelAndView load_step2(Request req, Response res) {
		String material_de_prenda = req.queryParams("tela");
		String abrigo = req.queryParams("nivelAbrigo");

		try {
			Material material = Material.valueOf(material_de_prenda);
			builder.conTela(material);
			res.cookie("material",material_de_prenda);
			int nivel_de_abrigo = Integer.parseInt(abrigo);
			res.cookie("nivelDeAbrigo",abrigo);
		}
		catch(Exception e) {
			System.out.println("Error->" + e);
			res.redirect("/prendas/step-2");
		}
		res.redirect("/prendas/step-3");

		return null;
	}
	
	public  ModelAndView load_step3(Request req, Response res) {
		Map<String,Object> viewModel = new HashMap<String, Object>();
		RepositorioDeUsuarios repo = RepositorioDeUsuarios.getInstance();
		try {
			Usuario usuarie = repo.buscarPorNombre(req.cookie("nombreUsuario"));
			int id_guardarropa = Integer.parseInt(req.queryParams("guardarropa"));
			Guardarropa guardarropa = usuarie.buscarGuardarropa(id_guardarropa);
			Prenda prenda = builder.crearPrenda();
			
			this.cargarPrenda(prenda, usuarie, guardarropa);
			builder = new PrendaBuilder();//sino devuelve siempre la misma prenda
			
		//	res.cookie("idGuardarropa", req.queryParams("guardarropa"));
			res.redirect("/prendas/step-4");
		}
		catch(Exception e){
			System.out.println("Error -> " + e);
		}
		
		return null;
	}
	public ModelAndView mostrarPrendas(Request req, Response res) {
		RepositorioDeUsuarios repo = RepositorioDeUsuarios.getInstance();
		Usuario usuarie = repo.buscarPorNombre(req.queryParams("nombreUsuario"));
		Map<String, Object> viewModel = new HashMap<String, Object>();
		List<Guardarropa> guardarropas = usuarie.getGuardarropas().stream().collect(Collectors.toList());
		return null;
	}
	public void cargarPrenda(Prenda prenda,Usuario user,Guardarropa guar) {
    	withTransaction(() -> {
    		//em.persist(prenda);    		
    		user.cargarPrenda(guar, prenda);
    	}); 		
	}
/*	public  ModelAndView prueba(Request req, Response res) {
		Map<String,Object> viewModel = new HashMap<String, Object>();
		
		return new ModelAndView(viewModel, "wizardPrenda.hbs");
	}
	public  ModelAndView pruebaPost(Request req, Response res) {
		Map<String,Object> viewModel = new HashMap<String, Object>();
		System.out.println("FUNCIONO!"+req.queryParams("a") + " y "+ req.params("b"));
		System.out.println(req.body());



			res.redirect("/perfil");
		
		return null;
	}*/
}
//kare2222277