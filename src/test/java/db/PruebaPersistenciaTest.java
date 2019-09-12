package db;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;
import domain.enums.*;
import domain.*;
import javax.persistence.EntityManager;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class PruebaPersistenciaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
	EntityManager em = entityManager();

	@Test
	public void testName2() throws Exception {
		Guardarropa bruno = new Guardarropa();
		Guardarropa giorno = new Guardarropa();
		em.persist(bruno);
		em.persist(giorno);
		Guardarropa paleta = em.createQuery("from Guardarropa order by Id", Guardarropa.class).getResultList().get(0);
		Guardarropa paleta2 = em.createQuery("from Guardarropa order by Id", Guardarropa.class).getResultList().get(1);
		Long nuevoId = paleta.getId();
		Long nuevoId2 = paleta2.getId();
		assertNotEquals(nuevoId2, nuevoId);
	}

	@Test
	public void testUsuario() throws Exception {
		Usuario bruno = new Usuario(TipoUsuario.PREMIUM, 25);
		Usuario giorno = new Usuario(TipoUsuario.GRATUITO, 15);
		Guardarropa soyElGuarda = new Guardarropa();
		bruno.agregarGuardarropa(soyElGuarda);
		em.persist(bruno);
		em.persist(giorno);
		ArrayList<Usuario> listaUsuariosPersistidos;
		listaUsuariosPersistidos = (ArrayList<Usuario>) em.createQuery("from Usuario order by Id", Usuario.class)
				.getResultList();

		int cantidadUsuariosPers = listaUsuariosPersistidos.size();
		int numEsperado = 2;
		assertEquals(cantidadUsuariosPers, numEsperado);
	}

	@Test
	public void guardaBienLosAtributos() throws Exception {
		Usuario koichi = new Usuario(TipoUsuario.GRATUITO, 15);
		Usuario luisito = new Usuario(TipoUsuario.PREMIUM, 125);
		Guardarropa soyElGuarda = new Guardarropa();
		koichi.agregarGuardarropa(soyElGuarda);
		em.persist(koichi);
		em.persist(luisito);
		Usuario koichiPersistido = em.createQuery("from Usuario order by Id", Usuario.class).getResultList().get(0);
		/*
		 * Usuario luisitoPersistido = em .createQuery("from Usuario order by Id",
		 * Usuario.class) .getResultList() .get(1);
		 */
		assertTrue(koichiPersistido.getGuardarropas().contains(soyElGuarda));
		assertEquals(koichi, koichiPersistido);
		assertNotEquals(koichi.getTipo(), luisito.getTipo());
	}

}
