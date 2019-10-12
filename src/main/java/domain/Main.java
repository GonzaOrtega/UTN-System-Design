package domain;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.hibernate.transform.ToListResultTransformer;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import domain.enums.TipoUsuario;
import domain.frecuenciasDeEventos.FrecuenciaAnual;

public class Main implements WithGlobalEntityManager{
	EntityManager em = entityManager();
	
	public static void main(String[] args) {
		Main main = new Main();
		Evento event = new Evento(new FrecuenciaAnual(02,01),"Medico");
		Usuario juan = new Usuario(TipoUsuario.GRATUITO,15,"el_Risas","123");
		Guardarropa armario = new Guardarropa();

		RepositorioDeUsuarios.getInstance().agregar(juan);
		//main.persistirGuar(armario,juan);
		return;
	}
    public void persistir(Evento juga,Usuario user) {
    	em.getTransaction().begin();
    	user.agendarEvento(juga);
    	em.getTransaction().commit();
    	
    }
    public void persistirGuar(Guardarropa juga,Usuario user) {
    	em.getTransaction().begin();
    	user.agregarGuardarropa(juga);
    	em.getTransaction().commit();
    	
    }
}
