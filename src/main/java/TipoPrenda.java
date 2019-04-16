public enum TipoPrenda{
		Remera			(Categoria.SUPERIOR),
		RemeraMangaCorta(Categoria.SUPERIOR),
		RemeraMangaLarga(Categoria.SUPERIOR),
		CamisaMangaCorta(Categoria.SUPERIOR),
		CamisaMangaLarga(Categoria.SUPERIOR),
		Pantalon		(Categoria.INFERIOR),
		Short			(Categoria.INFERIOR),
		PolleraCorta	(Categoria.INFERIOR),
		PolleraLarga	(Categoria.INFERIOR),
		Bermuda			(Categoria.INFERIOR),
		Calza			(Categoria.INFERIOR),
		Jardinero		(Categoria.INFERIOR),
		Zapatos			(Categoria.CALZADO),
		Ojotas			(Categoria.CALZADO),
		Zapatillas		(Categoria.CALZADO),
		Borcegos		(Categoria.CALZADO),
		BotasDeLluvia	(Categoria.CALZADO),
		Gorro			(Categoria.ACCESORIO),
		Gorra			(Categoria.ACCESORIO),
		Sombrero		(Categoria.ACCESORIO),
		Aros			(Categoria.ACCESORIO);
		//Etc.
		
		Categoria categoria;
		TipoPrenda(Categoria categoria){
			this.categoria=categoria;
		}
}