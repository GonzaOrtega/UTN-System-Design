import java.util.ArrayList;
import java.util.Arrays;



public enum TipoPrenda{
		Remera			(Categoria.SUPERIOR,
						new ArrayList<Material>(Arrays.asList(Material.ALGODON,Material.LYCRA,Material.SEDA, Material.SATEN,Material.GAMUZA,Material.CHIFFON, Material.POLIESTER,Material.MEZCLILLA))
						),
		RemeraMangaCorta(Categoria.SUPERIOR,
						new ArrayList<Material>(Arrays.asList(Material.ALGODON,Material.LYCRA,Material.SEDA, Material.SATEN,Material.GAMUZA,Material.CHIFFON, Material.POLIESTER,Material.MEZCLILLA))
						),
		RemeraMangaLarga(Categoria.SUPERIOR,
						new ArrayList<Material>(Arrays.asList(Material.ALGODON,Material.LYCRA,Material.SEDA, Material.SATEN,Material.GAMUZA,Material.CHIFFON, Material.POLIESTER,Material.MEZCLILLA))
						),
		CamisaMangaCorta(Categoria.SUPERIOR,
						new ArrayList<Material>(Arrays.asList(Material.ALGODON,Material.LYCRA,Material.SEDA, Material.SATEN,Material.GAMUZA, Material.JEAN,Material.MEZCLILLA,Material.LINO))
						),
		CamisaMangaLarga(Categoria.SUPERIOR,
						new ArrayList<Material>(Arrays.asList(Material.ALGODON,Material.LYCRA,Material.SEDA, Material.SATEN,Material.GAMUZA, Material.JEAN,Material.MEZCLILLA,Material.LINO))
						),
		Pantalon		(Categoria.INFERIOR,
						new ArrayList<Material>(Arrays.asList(Material.JEAN, Material.SPANDEX,Material.CUERO,Material.GABARDINA,Material.MODAL,Material.LINO,Material.MEZCLILLA))
						),
		Short			(Categoria.INFERIOR,
						new ArrayList<Material>(Arrays.asList(Material.JEAN, Material.SPANDEX,Material.CUERO,Material.GABARDINA,Material.MODAL,Material.LINO,Material.MEZCLILLA))
						),
		PolleraCorta	(Categoria.INFERIOR,
						new ArrayList<Material>(Arrays.asList(Material.LANILLA,Material.JEAN, Material.CUERO,Material.GABARDINA,Material.SPANDEX))
						),
		PolleraLarga	(Categoria.INFERIOR,
						new ArrayList<Material>(Arrays.asList(Material.LANILLA,Material.JEAN, Material.CUERO,Material.GABARDINA,Material.SPANDEX))
						),
		Bermuda			(Categoria.INFERIOR,
						new ArrayList<Material>(Arrays.asList(Material.JEAN, Material.SPANDEX,Material.CUERO,Material.GABARDINA,Material.MODAL,Material.LINO,Material.MEZCLILLA))
						),
		Calza			(Categoria.INFERIOR,
						new ArrayList<Material>(Arrays.asList(Material.ALGODON,Material.MEZCLILLA,Material.POLIESTER))
						),
		Jardinero		(Categoria.INFERIOR,
						new ArrayList<Material>(Arrays.asList())
						),
		Zapatos			(Categoria.CALZADO,
						new ArrayList<Material>(Arrays.asList(Material.CUERO))
						),
		Ojotas			(Categoria.CALZADO,
						new ArrayList<Material>(Arrays.asList(Material.CUERO,Material.POLIESTER,Material.CAUCHO))
						),
		Zapatillas		(Categoria.CALZADO,
						new ArrayList<Material>(Arrays.asList(Material.NYLON,Material.LONA,Material.CUERO))
						),
		Borcegos		(Categoria.CALZADO,
						new ArrayList<Material>(Arrays.asList(Material.CUERO))
						),
		Gorro			(Categoria.ACCESORIO,
						new ArrayList<Material>(Arrays.asList(Material.MEZCLILLA,Material.ALGODON, Material.LANA))
						),
		Gorra			(Categoria.ACCESORIO,
						new ArrayList<Material>(Arrays.asList(Material.CUERO,Material.NYLON,Material.POLIESTER,Material.ALGODON))
						),
		Sombrero		(Categoria.ACCESORIO,
						new ArrayList<Material>(Arrays.asList(Material.RAYON,Material.LANA))
						);
		//Etc.

		Categoria categoria;
		ArrayList <Material> materialesPermitidos;
		TipoPrenda(Categoria categoria, ArrayList<Material> materiales){
			this.categoria=categoria;
			this.materialesPermitidos=materiales;
		}
}
