import java.util.ArrayList;
import java.util.Arrays;



public enum TipoPrenda{
		Remera			(Categoria.SUPERIOR, 
						new ArrayList<Material>(Arrays.asList(Material.CUERO,Material.SPANDEX,Material.JEAN))
						),
		RemeraMangaCorta(Categoria.SUPERIOR,
						new ArrayList<Material>(Arrays.asList(Material.CUERO,Material.SPANDEX,Material.JEAN))
						),
		RemeraMangaLarga(Categoria.SUPERIOR,
						new ArrayList<Material>(Arrays.asList(Material.CUERO,Material.SPANDEX,Material.JEAN))
						),
		CamisaMangaCorta(Categoria.SUPERIOR,
						new ArrayList<Material>(Arrays.asList(Material.CUERO,Material.NYLON,Material.SPANDEX))
						),
		CamisaMangaLarga(Categoria.SUPERIOR,
						new ArrayList<Material>(Arrays.asList(Material.CUERO,Material.NYLON,Material.SPANDEX))
						),
		Pantalon		(Categoria.INFERIOR,
						new ArrayList<Material>(Arrays.asList(Material.LANA, Material.NYLON,Material.SEDA,Material.SATEN,Material.LANILLA))
						),
		Short			(Categoria.INFERIOR,
						new ArrayList<Material>(Arrays.asList(Material.LANA, Material.NYLON,Material.SEDA,Material.SATEN,Material.LANILLA))
						),
		PolleraCorta	(Categoria.INFERIOR,
						new ArrayList<Material>(Arrays.asList(Material.LANA, Material.NYLON,Material.SEDA,Material.SATEN,Material.LANILLA))
						),
		PolleraLarga	(Categoria.INFERIOR,
						new ArrayList<Material>(Arrays.asList(Material.LANA, Material.NYLON,Material.SEDA,Material.SATEN,Material.LANILLA))
						),
		Bermuda			(Categoria.INFERIOR,
						new ArrayList<Material>(Arrays.asList(Material.LANA, Material.NYLON,Material.SEDA,Material.SATEN,Material.LANILLA))
						),
		Calza			(Categoria.INFERIOR,
						new ArrayList<Material>(Arrays.asList(Material.LANA, Material.NYLON,Material.SEDA,Material.SATEN,Material.LANILLA,Material.JEAN))
						),
		Jardinero		(Categoria.INFERIOR,
						new ArrayList<Material>(Arrays.asList(Material.LANA, Material.NYLON,Material.SEDA,Material.SATEN,Material.LANILLA))
						),
		Zapatos			(Categoria.CALZADO,
						new ArrayList<Material>(Arrays.asList(Material.LANA, Material.NYLON,Material.SEDA,Material.SATEN,Material.LANILLA))
						),
		Ojotas			(Categoria.CALZADO,
						new ArrayList<Material>(Arrays.asList(Material.LANA, Material.NYLON, Material.SPANDEX, Material.LANILLA, Material.MEZCLILLA, Material.ALGODON))
						),
		Zapatillas		(Categoria.CALZADO,
						new ArrayList<Material>(Arrays.asList(Material.LANA, Material.NYLON, Material.SPANDEX, Material.LANILLA, Material.MEZCLILLA, Material.ALGODON))
						),
		Borcegos		(Categoria.CALZADO,
						new ArrayList<Material>(Arrays.asList(Material.LANA, Material.NYLON, Material.SPANDEX, Material.LANILLA, Material.MEZCLILLA, Material.ALGODON))
						),
		Gorro			(Categoria.ACCESORIO,
						new ArrayList<Material>(Arrays.asList(Material.SPANDEX,Material.JEAN))
						),
		Gorra			(Categoria.ACCESORIO,
						new ArrayList<Material>(Arrays.asList(Material.LANA))
						),
		Sombrero		(Categoria.ACCESORIO,
						new ArrayList<Material>(Arrays.asList(Material.LANA,Material.JEAN,Material.SPANDEX))
						);
		//Etc.
		
		Categoria categoria;
		ArrayList <Material> materialesProhibidos;
		TipoPrenda(Categoria categoria, ArrayList<Material> materiales){
			this.categoria=categoria;
			this.materialesProhibidos=materiales;
		}
}