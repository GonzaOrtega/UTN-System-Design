import java.util.*;

public class Prenda {
	
	Color colorPrimario;
	Color colorSecundario;
	TipoPrenda tipo;
	Material tela;

	HashMap<TipoPrenda, ArrayList<Material>> dictionary = new HashMap<TipoPrenda, ArrayList<Material>>(); // No tengo mucha imaginacion para los nombres
	
	public Prenda(Color unColorP,Color unColorS, TipoPrenda unTipo, Material unaTela){
		colorPrimario=unColorP;
		colorSecundario=unColorS;
		tipo=unTipo;
		tela=unaTela;
		// Al instanciarse el objeto, se carga el diccionario
		cargarValores(dictionary);
	}

	public boolean yaSeCargoLaPrendaSegun(Usuario unUsuario) {
		return unUsuario.guardarropas.stream().anyMatch(guardarropa->guardarropa.prendas.contains(this));
	}
	
	private void cargarValores(HashMap<TipoPrenda, ArrayList<Material>> dictionary) {
		dictionary.put(TipoPrenda.Remera, new ArrayList<Material>(Arrays.asList(
				Material.ALGODON,
				Material.LYCRA)));
		dictionary.put(TipoPrenda.RemeraMangaCorta, new ArrayList<Material>(Arrays.asList(
				Material.ALGODON,
				Material.LYCRA)));
		dictionary.put(TipoPrenda.RemeraMangaLarga, new ArrayList<Material>(Arrays.asList(
				Material.ALGODON,
				Material.LYCRA)));
		dictionary.put(TipoPrenda.Pantalon, new ArrayList<Material>(Arrays.asList(
				Material.GABARDINA)));
		dictionary.put(TipoPrenda.CamisaMangaCorta, new ArrayList<Material>(Arrays.asList(
				Material.ALGODON)));
		dictionary.put(TipoPrenda.CamisaMangaLarga, new ArrayList<Material>(Arrays.asList(
				Material.ALGODON)));
		dictionary.put(TipoPrenda.Calza, new ArrayList<Material>(Arrays.asList(
				Material.LYCRA)));
		// etc...
	}
	
	public ArrayList<Material> materialDeTipoPrenda() {
		return dictionary.get(tipo);
	}
}
