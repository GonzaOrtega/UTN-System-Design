package db;

import static org.junit.Assert.*;
import java.util.*;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import domain.apisClima.*;
import domain.enums.*;
import domain.frecuenciasDeEventos.FrecuenciaUnicaVez;
import domain.*;
import javax.persistence.EntityManager;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class GuardarropaPersistenciaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
	
	EntityManager em = entityManager();
	Usuario juan = new Usuario(TipoUsuario.PREMIUM,0);
	ProveedorClima APIDeMentiritas = new MockAPI(21,23,false);
	Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.ALGODON).conColorPrimario(Color.ROJO).conColorSecundario(Color.AMARILLO).crearPrenda();
	Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).crearPrenda();
	Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.NEGRO).conTela(Material.ALGODON).crearPrenda();
	Prenda camisaLarga = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaLarga).conColorPrimario(Color.BLANCO).conTela(Material.SATEN).crearPrenda();
	Prenda ojotas = new PrendaBuilder().conTipo(TipoPrenda.Ojotas).conTela(Material.CAUCHO).conColorPrimario(Color.NEGRO).crearPrenda();
	Prenda jean = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.AZUL).crearPrenda();
	Guardarropa armario = new Guardarropa();
	Evento eventoConFrecuenciaUnica = new Evento(new FrecuenciaUnicaVez(2019,2,16),"Sin descripcion");//Fecha "16-02-2019" -> Es decir, un evento finalizado
	Usuario karen = new Usuario(TipoUsuario.PREMIUM,0);
	
	
	@Before
	public void setUp(){
		juan.cargarPrenda(armario, camisaCorta);
		juan.cargarPrenda(armario, zapatos);
		juan.cargarPrenda(armario, gorra);
		juan.cargarPrenda(armario, camisaLarga);
		juan.cargarPrenda(armario, ojotas);
		juan.agregarGuardarropa(armario);
		em.persist(juan);
		//em.persist(eventoConFrecuenciaUnica);
		Sugeridor.getInstance().setProveedorDeClima(APIDeMentiritas);
	}

	@Test
	public void siJuanAgregaUnJeanAArmarioEsteSeVeraModificadoYPersistido() {
		withTransaction(() -> {juan.cargarPrenda(armario, jean);});
		assertTrue(armario.prendas().contains(jean));
	}

	//TODO: ver por que da 3 y no 2
	@Test
	public void siJuanAgregaASuGuardarropasOtroArmarioHabraDosArmariosPersistidos() {
		Guardarropa otroArmario = new Guardarropa();
		withTransaction(() -> {juan.agregarGuardarropa(otroArmario);});
		assertEquals(2,em.createQuery("from Guardarropa order by Id",Guardarropa.class).getResultList().size());
	}
	
}
