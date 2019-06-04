public class Prenda {
	
	private Color colorPrimario;
	private Color colorSecundario;
	private TipoPrenda tipo;
	private Material tela;
	private Foto foto;
	private int nivelAbrigo;
	private boolean esBase;
	
	//Get y set de todos los atributos
	
	public int getNivelAbrigo() {
		return nivelAbrigo;
	}
	public void setNivelAbrigo(int nivelAbrigo) {
		this.nivelAbrigo = nivelAbrigo;
	}
	public boolean getEsBase() {
		return esBase;
	}
	public void setEsBase(boolean esBase) {
		this.esBase = esBase;
	}
	
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
