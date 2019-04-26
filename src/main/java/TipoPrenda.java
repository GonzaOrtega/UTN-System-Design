import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public enum TipoPrenda{
		Remera			(Categoria.SUPERIOR,
						Arrays.asList(Material.ALGODON,Material.LYCRA,Material.SEDA, Material.SATEN,Material.GAMUZA,Material.CHIFFON, Material.POLIESTER,Material.MEZCLILLA)
						),
		RemeraMangaCorta(Categoria.SUPERIOR,
						Arrays.asList(Material.ALGODON,Material.LYCRA,Material.SEDA, Material.SATEN,Material.GAMUZA,Material.CHIFFON, Material.POLIESTER,Material.MEZCLILLA)
						),
		RemeraMangaLarga(Categoria.SUPERIOR,
						Arrays.asList(Material.ALGODON,Material.LYCRA,Material.SEDA, Material.SATEN,Material.GAMUZA,Material.CHIFFON, Material.POLIESTER,Material.MEZCLILLA)
						),
		CamisaMangaCorta(Categoria.SUPERIOR,
						Arrays.asList(Material.ALGODON,Material.LYCRA,Material.SEDA, Material.SATEN,Material.GAMUZA, Material.JEAN,Material.MEZCLILLA,Material.LINO)
						),
		CamisaMangaLarga(Categoria.SUPERIOR,
						Arrays.asList(Material.ALGODON,Material.LYCRA,Material.SEDA, Material.SATEN,Material.GAMUZA, Material.JEAN,Material.MEZCLILLA,Material.LINO)
						),
		Pantalon		(Categoria.INFERIOR,
						Arrays.asList(Material.JEAN, Material.SPANDEX,Material.CUERO,Material.GABARDINA,Material.MODAL,Material.LINO,Material.MEZCLILLA)
						),
		Short			(Categoria.INFERIOR,
						Arrays.asList(Material.JEAN, Material.SPANDEX,Material.CUERO,Material.GABARDINA,Material.MODAL,Material.LINO,Material.MEZCLILLA)
						),
		PolleraCorta	(Categoria.INFERIOR,
						Arrays.asList(Material.LANILLA,Material.JEAN, Material.CUERO,Material.GABARDINA,Material.SPANDEX)
						),
		PolleraLarga	(Categoria.INFERIOR,
						Arrays.asList(Material.LANILLA,Material.JEAN, Material.CUERO,Material.GABARDINA,Material.SPANDEX)
						),
		Bermuda			(Categoria.INFERIOR,
						Arrays.asList(Material.JEAN, Material.SPANDEX,Material.CUERO,Material.GABARDINA,Material.MODAL,Material.LINO,Material.MEZCLILLA)
						),
		Calza			(Categoria.INFERIOR,
						Arrays.asList(Material.ALGODON,Material.MEZCLILLA,Material.POLIESTER)
						),
		Jardinero		(Categoria.INFERIOR,
						Arrays.asList(Material.JEAN)
						),
		Zapatos			(Categoria.CALZADO,
						Arrays.asList(Material.CUERO)
						),
		Ojotas			(Categoria.CALZADO,
						Arrays.asList(Material.CUERO,Material.POLIESTER,Material.CAUCHO)
						),
		Zapatillas		(Categoria.CALZADO,
						Arrays.asList(Material.NYLON,Material.LONA,Material.CUERO)
						),
		Borcegos		(Categoria.CALZADO,
						Arrays.asList(Material.CUERO)
						),
		Gorro			(Categoria.ACCESORIO,
						Arrays.asList(Material.MEZCLILLA,Material.ALGODON, Material.LANA)
						),
		Gorra			(Categoria.ACCESORIO,
						Arrays.asList(Material.CUERO,Material.NYLON,Material.POLIESTER,Material.ALGODON)
						),
		Sombrero		(Categoria.ACCESORIO,
						Arrays.asList(Material.RAYON,Material.LANA)
						);
		//Etc.

		Categoria categoria;
		List <Material> materialesPermitidos;
		TipoPrenda(Categoria categoria, List<Material> materiales){
			this.categoria=categoria;
			this.materialesPermitidos=materiales;
		}
}
