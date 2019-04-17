public class Prenda {
	
	Color colorPrimario;
	Color colorSecundario;
	TipoPrenda tipo;
	Material tela;

	
	public Prenda(Color unColorP,Color unColorS, TipoPrenda unTipo, Material unaTela){
		colorPrimario=unColorP;
		colorSecundario=unColorS;
		tipo=unTipo;
		tela=unaTela;
	}

	public boolean yaSeCargoLaPrendaSegun(Usuario unUsuario) {
		return unUsuario.guardarropas.stream().anyMatch(guardarropa->guardarropa.prendas.contains(this));
	}
}
