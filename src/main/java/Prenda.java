import enums.Color;
import enums.Material;
import enums.TipoPrenda;

public class Prenda {
	
	private Color colorPrimario;
	private Color colorSecundario;
	private TipoPrenda tipo;
	private Material tela;
	private Foto foto;
	private int nivelAbrigo;
	
	//Get y set de todos los atributos
	
	public int getNivelAbrigo() {
		return nivelAbrigo;
	}
	public void setNivelAbrigo(int nivelAbrigo) {
		this.nivelAbrigo = nivelAbrigo;
	}
	public boolean getEsBase() {
		return this.esBase(this.getTipo()); 
	}
	private boolean esBase(TipoPrenda prenda) {
		boolean aux = prenda == TipoPrenda.Remera 
				|| prenda == TipoPrenda.RemeraMangaCorta 
				|| prenda == TipoPrenda.RemeraMangaLarga
				|| prenda == TipoPrenda.CamisaMangaCorta
				|| prenda == TipoPrenda.CamisaMangaLarga;		
		return aux;
	}
	public boolean esDeVerano() {
		return this.tipo == TipoPrenda.Ojotas
				||this.tipo == TipoPrenda.Short
				||this.tipo == TipoPrenda.PolleraCorta
				||this.tipo == TipoPrenda.Bermuda;
		
	}
	/*public boolean getEsAbrigo() {
		
	}
	public int posicion() {
		if(this.getEsBase())
			return 0;
		if(this.getEsAbrigo())
			return 1;
		else
			return 2;
	}*/
	
	public Color getColorPrimario() {
		return colorPrimario;
	}
	public void setColorPrimario(Color colorPrimario) {
		this.colorPrimario = colorPrimario;
	}
	public Color getColorSecundario() {
		return colorSecundario;
	}
	public void setColorSecundario(Color colorSecundario) {
		this.colorSecundario = colorSecundario;
	}
	public TipoPrenda getTipo() {
		return tipo;
	}
	public void setTipo(TipoPrenda tipo) {
		this.tipo = tipo;
	}
	public Material getTela() {
		return tela;
	}
	public void setTela(Material tela) {
		this.tela = tela;
	}
	public Foto getFoto() {
		return foto;
	}
	public void setFoto(Foto foto) {
		this.foto = foto;
	}
	
	
}
