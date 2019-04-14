public class Tipo {
	enum Categoria{
		SUPERIOR,CALZADO,INFERIOR,ACCESORIO
	}
	public String nombre;
	Categoria categoria;
	public  Tipo(String stri, Categoria cate) {
		nombre = stri;
		categoria = cate;
	}
}