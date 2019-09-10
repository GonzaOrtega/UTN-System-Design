package db;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.*;

import org.junit.Before;
import org.junit.Test;
import domain.apisClima.*;
import domain.enums.*;
import domain.exceptions.*;
import domain.*;
import javax.persistence.EntityManager;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import antlr.collections.List;


public class GuardarropaPersistenciaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
	EntityManager em = entityManager();
	Usuario juan = new Usuario(TipoUsuario.PREMIUM,0);
	ProveedorClima APIDeMentiritas = new MockAPI(21,23,false);
	Sugeridor sugeridor = new Sugeridor(APIDeMentiritas);
	Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.ALGODON).conColorPrimario(Color.ROJO).conColorSecundario(Color.AMARILLO).crearPrenda();
	Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).crearPrenda();
	Prenda zapatillas = new PrendaBuilder().conTipo(TipoPrenda.Zapatillas).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).crearPrenda();
	Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.NEGRO).conTela(Material.ALGODON).crearPrenda();
	Prenda sombrero= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.NEGRO).conTela(Material.ALGODON).crearPrenda();
	Prenda camisaLarga = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaLarga).conColorPrimario(Color.BLANCO).conTela(Material.SATEN).crearPrenda();
	Prenda camisaDeLara = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaLarga).conColorPrimario(Color.BLANCO).conTela(Material.SATEN).crearPrenda();
	Prenda ojotas = new PrendaBuilder().conTipo(TipoPrenda.Ojotas).conTela(Material.CAUCHO).conColorPrimario(Color.NEGRO).crearPrenda();
	Prenda jean = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.AZUL).crearPrenda();
	Guardarropa armario = new Guardarropa();

	
	@Before
	public void setUp(){
		juan.agregarGuardarropa(armario);
		juan.cargarPrenda(armario, camisaCorta);
		juan.cargarPrenda(armario, zapatos);
		juan.cargarPrenda(armario, gorra);
		juan.cargarPrenda(armario, camisaLarga);
		juan.cargarPrenda(armario, ojotas);
		em.persist(juan);
		em.persist(armario);
	}

	
	@Test
	  public void siSePersistenDosGuardarropasDistintosTendranDosIDsDistintos() throws Exception {
		  Guardarropa unGuardarropa = new Guardarropa();
		  em.persist(unGuardarropa);
		  
		  Set<Guardarropa> guardarropas = em
				  .createQuery("from Guardarropa", Guardarropa.class)
				  .getResultList().stream().collect(Collectors.toSet());
		  
		  Guardarropa guardarropaQuery = em
				  .createQuery("from Guardarropa order by id", Guardarropa.class)
				  .getResultList()
				  .get(2); // ver que onda esto!!!!

		  Long idArmario = armario.getId();

		  assertNotEquals(armario.getId(),guardarropaQuery.getId());
		  
	}
	
	@Test
	 public void siSeSolicitanAtuendosSePersisten() {
		  withTransaction(() -> {
				juan.cargarPrenda(armario, jean);
		  });
		  
		 Set<Prenda> prendasQuery = em
				  .createQuery("from Guardarropa order by Id", Guardarropa.class)
				  .getResultList()
				  .get(0).prendas();
		 
		 assertTrue(prendasQuery.contains(jean));


		  
	}
	
	
	
}
