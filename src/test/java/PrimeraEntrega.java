import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;

public class PrimeraEntrega {

	@Before
	public void setUp() {
		
		Usuario juan = new Usuario(198,"JFQ8");
	    
		Guardarropa armario = new Guardarropa();
	    armario.agregarPrenda(new Prenda(Color.ROSA,Color.VERDE, TipoPrenda.CamisaMangaLarga, Material.ALGODON));
	    armario.agregarPrenda(new Prenda(Color.ROSA,Color.AZUL, TipoPrenda.Pantalon, Material.ALGODON));
	    
	    Guardarropa otroArmario = new Guardarropa();
	    otroArmario.agregarPrenda(new Prenda(Color.AZUL,Color.VERDE, TipoPrenda.Zapatillas, Material.ALGODON));
	    otroArmario.agregarPrenda(new Prenda(Color.AZUL,Color.VERDE, TipoPrenda.Gorro, Material.ALGODON));
	   
	    juan.agregarGuardarropa(armario);
	    juan.agregarGuardarropa(otroArmario);
	    
	}
	
	@Test
	public void test() {
		
	}

}
