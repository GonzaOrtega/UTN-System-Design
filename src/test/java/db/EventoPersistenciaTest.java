package db;
import static org.junit.Assert.assertTrue;
import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import domain.Evento;
import domain.Usuario;
import domain.apisClima.MockAPI;
import domain.apisClima.ProveedorClima;
import domain.enums.TipoUsuario;
import domain.frecuenciasDeEventos.FrecuenciaAnual;
import domain.frecuenciasDeEventos.FrecuenciaDiaria;

public class EventoPersistenciaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
	EntityManager em = entityManager();
	ProveedorClima APIDeMentiritas = new MockAPI(21,23,false);
	Evento eventoAnual = new Evento(new FrecuenciaAnual(02,01),"Medico");
	Evento eventoDiario = new Evento(new FrecuenciaDiaria(0), "Trabajo");
	Usuario juan = new Usuario(TipoUsuario.PREMIUM,0,"juan2","123");
	@Before
	public void setUp(){
		juan.agendarEvento(eventoDiario);
		juan.agendarEvento(eventoAnual);
		em.persist(juan);
	}
	
	@Test
	public void cargarEventos() {
		int cantidadEventos = em.createQuery("from Evento order by Id", Evento.class)
					.getResultList().size();
		assertTrue(cantidadEventos == 2);
	}
	@Test
	public void desagendarEventos() {
//		juan.agendarEvento(eventoDiario);
	//	juan.agendarEvento(eventoAnual);
		Usuario usuario = em.createQuery("from Usuario where nombreUsuario = 'juan'",Usuario.class)
					.getSingleResult();
		assertTrue(usuario.eventos().size()==2);
		juan.desagendarEvento(eventoDiario);
		usuario = em.createQuery("from Usuario  where nombreUsuario = 'juan'",Usuario.class)
				.getSingleResult();
		assertTrue(usuario.eventos().size()==1);
	}
	
}
