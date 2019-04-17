import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;

public class PrimeraEntrega {

	@Before
	public void setUp() {
		// Prendas:
		// new Prenda(Color.ROSA,Color.VERDE, TipoPrenda.CamisaMangaLarga, Material.ALGODON)
		// new Prenda(Color.ROSA,Color.AZUL, TipoPrenda.Pantalon, Material.ALGODON)
		// new Prenda(Color.AZUL,Color.VERDE, TipoPrenda.Zapatillas, Material.ALGODON)
		// new Prenda(Color.AZUL,Color.VERDE, TipoPrenda.Gorro, Material.ALGODON)
		
		Usuario juan = new Usuario(198,"JFQ8");
		Guardarropa armario = new Guardarropa();	    
	    Guardarropa otroArmario = new Guardarropa();
	   
	    juan.agregarGuardarropa(armario);
	    juan.agregarGuardarropa(otroArmario);
	}
	
	@Test (expected = YaSeEncuentraCargadaException.class)
	public void no_se_pueden_cargar_prendas_ya_cargadas()  {
		Usuario juan = new Usuario(198,"JFQ8");
		Guardarropa armario = new Guardarropa();	    
	    juan.agregarGuardarropa(armario);
	    Prenda camisaRosa = new Prenda(Color.ROSA,Color.VERDE, TipoPrenda.CamisaMangaLarga, Material.ALGODON);
		juan.cargarPrenda(armario,camisaRosa);
		juan.cargarPrenda(armario,camisaRosa);
	}

}
