public class Prenda {
	
	public enum Color{
		AMARILLO,
		VERDE,
		NARANJA,
		AZUL,
		VIOLETA,
		ROSA,
		LILA;
		// Etc.
	}
	
	public enum Material{
		ALGODON,
		LYCRA,
		GABARDINA,
		LINO,
		FRANELA;
		// Etc.
	}
	
	Color colorPrimario;
	Color colorSecundario;
	Tipo tipo;
	Material tela;

	
	public Prenda(Color unColorP,Color unColorS, Tipo unTipo, Material unaTela){
		//this.validarParametros(unColorP,unTipo,unaTela);
		colorPrimario=unColorP;
		colorSecundario=unColorS;
		tipo=unTipo;
		tela=unaTela;
	}
	
	public void validarParametros(Color c,Tipo t, Material m){
		// Comportamiento..
	}
	
	
}
