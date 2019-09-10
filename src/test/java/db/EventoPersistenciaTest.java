package db;
import static org.junit.Assert.assertTrue;
import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import domain.Evento;
import domain.Sugeridor;
import domain.apisClima.MockAPI;
import domain.apisClima.ProveedorClima;
import domain.frecuenciasDeEventos.FrecuenciaAnual;
import domain.frecuenciasDeEventos.FrecuenciaDiaria;

public class EventoPersistenciaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
	EntityManager em = entityManager();
	ProveedorClima APIDeMentiritas = new MockAPI(21,23,false);
	Sugeridor sugeridor = new Sugeridor(APIDeMentiritas);
	Evento eventoAnual = new Evento(sugeridor, new FrecuenciaAnual(02,01),"Medico");
	Evento eventoDiario = new Evento(sugeridor,new FrecuenciaDiaria(0), "Trabajo");

	@Before
	public void setUp(){
		em.persist(eventoDiario);
		em.persist(eventoAnual);
	}
	
	@Test
	public void cargarEventos() {
		int cantidadEventos = em.createQuery("from Evento order by Id", Evento.class)
					.getResultList().size();
		assertTrue(cantidadEventos == 2);
	}
	
}
