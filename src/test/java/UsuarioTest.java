import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {

	Usuario juan = new Usuario(198,"JFQ8");
	Guardarropa armario = new Guardarropa();
	PrendaBuilder builderCamisaRosa = new PrendaBuilder().conColorPrimario(Color.ROSA).conColorSecundario(Color.VERDE).conTipo(TipoPrenda.CamisaMangaLarga).conTela(Material.ALGODON);
	Prenda camisaRosa = builderCamisaRosa.crearPrenda();
	
	@Before
	public void setUp() throws Exception {
		juan.agregarGuardarropa(armario);
	}
	
	@Test (expected = YaSeEncuentraCargadaException.class)
	public void test_no_se_pueden_cargar_prendas_ya_cargadas()  {
//		Usuario juan = new Usuario(198,"JFQ8");
//		Guardarropa armario = new Guardarropa();	    
//	    juan.agregarGuardarropa(armario);
		juan.cargarPrenda(armario,camisaRosa);
		juan.cargarPrenda(armario,camisaRosa);
	}
	
	@Test (expected = YaSeEncuentraCargadaException.class)
	public void no_se_pueden_compartir_prendas_entre_guardarropas()  {
//		Usuario juan = new Usuario(198,"JFQ8");
//		Guardarropa armario = new Guardarropa();
	    Guardarropa otroArmario = new Guardarropa();
	    juan.agregarGuardarropa(armario);
	    juan.agregarGuardarropa(otroArmario);
	    //Prenda camisaRosa = new Prenda(Color.ROSA,Color.VERDE, TipoPrenda.CamisaMangaLarga, Material.ALGODON);
		juan.cargarPrenda(armario,camisaRosa);
		juan.cargarPrenda(otroArmario,camisaRosa);
	}

}
