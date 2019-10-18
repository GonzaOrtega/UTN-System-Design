package db;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

import javax.persistence.Query;
import org.junit.Test;
import domain.enums.*;
import domain.frecuenciasDeEventos.FrecuenciaUnicaVez;
import domain.*;
import javax.persistence.EntityManager;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class RepositorioUsuarioTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
	EntityManager em = entityManager();

	@Test
	public void testUsuario() throws Exception {
		RepositorioDeUsuarios repo = RepositorioDeUsuarios.getInstance();
		Usuario bruno = new Usuario(TipoUsuario.PREMIUM, 25,"jua12","123");
		Usuario giorno = new Usuario(TipoUsuario.GRATUITO, 15,"juanes","123");
		Guardarropa soyElGuarda = new Guardarropa();
		bruno.agregarGuardarropa(soyElGuarda);
		em.persist(bruno);
		em.persist(giorno);
		HashSet<Usuario> listaUsuariosPersistidos;
		listaUsuariosPersistidos = (HashSet<Usuario>) repo.usuarios();
		int cantidadUsuariosPers = listaUsuariosPersistidos.size();
		int numEsperado = 2;
		assertEquals(numEsperado,cantidadUsuariosPers);
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
		em.persist(guidoMista);
		guidoMista.agendarEvento(evento3);
		Long idNro =guidoMista.getId(); 
		String id = idNro.toString();
		guidoMista = null;
		Usuario bdUser = em.createQuery("from Usuario where id = " +id, Usuario.class).getResultList().get(0);
		
		int cantidad = bdUser.eventos().size();

		assertTrue(cantidad == 3);
	}
	@Test
	public void buscarPorNombre(){
		RepositorioDeUsuarios repo = RepositorioDeUsuarios.getInstance();
		//Usuario juan =em.find(Usuario.class, new Long(9));
		Usuario juan = repo.buscarPorNombre("juan");
		assertTrue(juan.getId()==9);
	}
	//String hql="Select log.userId from Login log where log.username=:username and log.password=:password"

}
