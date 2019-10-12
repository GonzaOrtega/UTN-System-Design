package db;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;
import domain.enums.*;
import domain.frecuenciasDeEventos.FrecuenciaUnicaVez;
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
		assertNotEquals(paleta, paleta2);
	}

	@Test
	public void testUsuario() throws Exception {
		Usuario bruno = new Usuario(TipoUsuario.PREMIUM, 25,"juan","123");
		Usuario giorno = new Usuario(TipoUsuario.GRATUITO, 15,"juan","123");
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
		Usuario koichi = new Usuario(TipoUsuario.GRATUITO, 15,"juan","123");
		Usuario luisito = new Usuario(TipoUsuario.PREMIUM, 125,"juan","123");
		Guardarropa soyElGuarda = new Guardarropa();
		koichi.agregarGuardarropa(soyElGuarda);
		em.persist(koichi);
		em.persist(luisito);
		Usuario koichiPersistido = em.createQuery("from Usuario order by Id", Usuario.class).getResultList().get(0);
		assertTrue(koichiPersistido.getGuardarropas().contains(soyElGuarda));
		assertEquals(koichi, koichiPersistido);
		assertNotEquals(koichi.getTipo(), luisito.getTipo());
	}
	@Test
	public void primerTestRepositorio() throws Exception {
		RepositorioDeUsuarios repo = RepositorioDeUsuarios.getInstance();
		Usuario koichi = new Usuario(TipoUsuario.GRATUITO, 15,"juan","123");
		Usuario luisito = new Usuario(TipoUsuario.PREMIUM, 125,"juan","123");
		repo.agregar(koichi);
		repo.agregar(luisito);
		int cantidad = repo.usuarios().size();
		System.out.println(cantidad);
		assertTrue(cantidad == 2);
	}
	@Test
	public void segundoTestRepositorio() throws Exception {
		RepositorioDeUsuarios repo = RepositorioDeUsuarios.getInstance();
		Usuario guidoMista = new Usuario(TipoUsuario.GRATUITO, 15,"juan","123");
		Evento evento1 = new Evento(new FrecuenciaUnicaVez(2019,10,24),"Almorzar en Restaurante.");//"24-05-2019"
		Evento evento2 = new Evento(new FrecuenciaUnicaVez(2019,10,25),"Entrenar tiro.");
		Evento evento3 = new Evento(new FrecuenciaUnicaVez(2019,10,25),"Entrenar tiro.");
		guidoMista.agendarEvento(evento1);
		guidoMista.agendarEvento(evento2);
		repo.agregar(guidoMista);
		guidoMista.agendarEvento(evento3);
		guidoMista = null;
		Usuario bdUser = em.createQuery("from Usuario order by Id", Usuario.class).getResultList().get(0);
		
		int cantidad = bdUser.eventos().size();

		assertTrue(cantidad == 3);
	}
}
