import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;
import domain.apisClima.*;
import domain.enums.*;
import domain.exceptions.*;
import domain.*;
import javax.persistence.EntityManager;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;


public class PruebaPersistenciaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
	@Test
	  public void testName2() throws Exception {
		  EntityManager em = entityManager();
		  Guardarropa bruno = new Guardarropa();
		  Guardarropa giorno = new Guardarropa();
		  em.persist(bruno);
		  em.persist(giorno);
		  Guardarropa paleta = em
				  .createQuery("from Guardarropa order by Id", Guardarropa.class)
				  .getResultList()
				  .get(0);
		  Guardarropa paleta2 = em
				  .createQuery("from Guardarropa order by Id", Guardarropa.class)
				  .getResultList()
				  .get(1); 
		  Long nuevoId = paleta.getId();
		  Long nuevoId2 = paleta2.getId();
		  assertNotEquals(nuevoId2,nuevoId);
}
}
