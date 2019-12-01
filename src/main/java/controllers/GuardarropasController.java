package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import domain.Guardarropa;
import domain.RepositorioDeUsuarios;
import domain.Prenda;
import domain.PrendaBuilder;
import domain.Usuario;
import domain.enums.Color;
import domain.enums.Material;
import domain.enums.TipoPrenda;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class GuardarropasController {
	
	public ModelAndView show(Request req, Response res) {
		Map<String, Object> viewModel = new HashMap<String, Object>();		
		RepositorioDeUsuarios repo = RepositorioDeUsuarios.getInstance();
		Usuario usuarie = repo.buscarPorNombre(req.cookie("nombreUsuario"));
		List<Guardarropa> guardarropas = usuarie.getGuardarropas().stream().collect(Collectors.toList());
		Boolean hayGuardarropas = !guardarropas.isEmpty();
		viewModel.put("hayGuardarropas", hayGuardarropas);
		viewModel.put("guardarropas", guardarropas);
		
		return new ModelAndView(viewModel,"guardarropa.hbs");
	}
	
	public ModelAndView pru(Request req, Response res) {
		Map<String,Object> viewModel = new HashMap<String, Object>();
		RepositorioDeUsuarios repo = RepositorioDeUsuarios.getInstance();
		Usuario usuarie = repo.buscarPorNombre("juan");
		List<Guardarropa> listaGuard = usuarie.getGuardarropas().stream().collect(Collectors.toList());
		List<GuardarropaInformation> guardarropas = listaGuard.stream().map(g->convertirInfo(g)).collect(Collectors.toList());
		List<Set<Prenda>> lista = agregarPrendas();
		//viewModel.put("guardarropa",guardarropas);
		viewModel.put("guardarropas", listaGuard);
		//viewModel.put("listaSets", lista);

		return new ModelAndView(viewModel,"listaGuardarropas.hbs");
	}
	
	private List<Set<Prenda>> agregarPrendas(){//Metodo para pruebas. LUEGO BORRAR
		List<Set<Prenda>> lista = new ArrayList<Set<Prenda>>();
		Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.Cuero).conColorPrimario(Color.Amarillo).crearPrenda();
		Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.Negro).conTela(Material.Algodon).crearPrenda();
		Prenda camisaLarga = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaLarga).conColorPrimario(Color.Blanco).conTela(Material.Saten).crearPrenda();
		Prenda ojotas = new PrendaBuilder().conTipo(TipoPrenda.Ojotas).conTela(Material.Caucho).conColorPrimario(Color.Negro).crearPrenda();
		Set<Prenda> set1 = new HashSet<Prenda>();
		Set<Prenda> set2 = new HashSet<Prenda>();
		set1.add(zapatos);
		set1.add(gorra);
		set1.add(camisaLarga);
		set2.add(ojotas);
		lista.add(set1);
		lista.add(set2);
		return lista;

	}

	private GuardarropaInformation convertirInfo(Guardarropa guard) {
		List<Prenda> listaPrendas = new ArrayList(guard.getPrendas());
		return new GuardarropaInformation(guard.getNombre(), listaPrendas);
	}
}
