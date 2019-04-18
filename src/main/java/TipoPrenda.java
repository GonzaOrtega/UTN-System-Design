import java.util.ArrayList;
import java.util.Arrays;



public enum TipoPrenda{
		Remera			(Categoria.SUPERIOR, 
						new ArrayList<Material>(Arrays.asList(Material.CUERO))
						),
		RemeraMangaCorta(Categoria.SUPERIOR,
						new ArrayList<Material>(Arrays.asList(Material.CUERO))
						),
		RemeraMangaLarga(Categoria.SUPERIOR,
						new ArrayList<Material>(Arrays.asList(Material.CUERO))),
		CamisaMangaCorta(Categoria.SUPERIOR,
						new ArrayList<Material>(Arrays.asList(Material.CUERO))
						),
		CamisaMangaLarga(Categoria.SUPERIOR,
						new ArrayList<Material>(Arrays.asList(Material.CUERO))
						),
		Pantalon		(Categoria.INFERIOR,
						new ArrayList<Material>(Arrays.asList(Material.LANA))
						),
		Short			(Categoria.INFERIOR,
						new ArrayList<Material>(Arrays.asList(Material.LANA))
						),
		PolleraCorta	(Categoria.INFERIOR,
						new ArrayList<Material>(Arrays.asList(Material.LANA))
						),
		PolleraLarga	(Categoria.INFERIOR,
						new ArrayList<Material>(Arrays.asList(Material.LANA))
						),
		Bermuda			(Categoria.INFERIOR,
						new ArrayList<Material>(Arrays.asList(Material.LANA))
						),
		Calza			(Categoria.INFERIOR,
						new ArrayList<Material>(Arrays.asList(Material.LANA))
						),
		Jardinero		(Categoria.INFERIOR,
						new ArrayList<Material>(Arrays.asList(Material.LANA))
						),
		Zapatos			(Categoria.CALZADO,
						new ArrayList<Material>(Arrays.asList(Material.LANA))
						),
		Ojotas			(Categoria.CALZADO,
						new ArrayList<Material>(Arrays.asList(Material.LANA))
						),
		Zapatillas		(Categoria.CALZADO,
						new ArrayList<Material>(Arrays.asList(Material.LANA))
						),
		Borcegos		(Categoria.CALZADO,
						new ArrayList<Material>(Arrays.asList(Material.LANA))
						),
		BotasDeLluvia	(Categoria.CALZADO,
						new ArrayList<Material>(Arrays.asList(Material.LANA))
						),
		Gorro			(Categoria.ACCESORIO,
						new ArrayList<Material>(Arrays.asList(Material.LANA))
						),
		Gorra			(Categoria.ACCESORIO,
						new ArrayList<Material>(Arrays.asList(Material.LANA))
						),
		Sombrero		(Categoria.ACCESORIO,
						new ArrayList<Material>(Arrays.asList(Material.LANA))
						),
		Aros			(Categoria.ACCESORIO,
						new ArrayList<Material>(Arrays.asList(Material.LANA))
						);
		//Etc.
		
		Categoria categoria;
		ArrayList <Material> materialesProhibidos;
		TipoPrenda(Categoria categoria, ArrayList<Material> materiales){
			this.categoria=categoria;
			this.materialesProhibidos=materiales;
		}
}