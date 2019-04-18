public class Prenda {
	
	Color colorPrimario;
	Color colorSecundario;
	TipoPrenda tipo;
	Material tela;
	
	public Prenda(){}
	
	public boolean yaSeCargoLaPrendaSegun(Usuario unUsuario) {
		return unUsuario.guardarropas.stream().anyMatch(guardarropa->guardarropa.prendas.contains(this));
	}
	

}
