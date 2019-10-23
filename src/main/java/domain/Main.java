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
		Usuario juan2 = new Usuario(TipoUsuario.GRATUITO,15,"el_Risas","123");
		Guardarropa armario = new Guardarropa();
		Usuario juan = RepositorioDeUsuarios.getInstance().buscarPorNombre("juan");
		
		//RepositorioDeUsuarios.getInstance().agregar(juan);
		Prenda laPrenda = new Prenda();
		main.persistir(juan, laPrenda);
		//main.persistirGuar(armario,juan);
		return;
	}
    public void persistir(Usuario user,Prenda prenda) {
    	em.getTransaction().begin();
    	user.cargarPrenda(user.buscarGuardarropa(22), prenda);
    	em.getTransaction().commit();
    	
    }
    public void persistirGuar(Guardarropa juga,Usuario user) {
    	em.getTransaction().begin();
    	user.agregarGuardarropa(juga);
    	em.getTransaction().commit();
    	
    }
}
