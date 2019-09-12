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
	Sugeridor sugeridor = new Sugeridor(APIDeMentiritas);
	Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.ALGODON).conColorPrimario(Color.ROJO).conColorSecundario(Color.AMARILLO).crearPrenda();
	Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).crearPrenda();
	Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.NEGRO).conTela(Material.ALGODON).crearPrenda();
	Prenda camisaLarga = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaLarga).conColorPrimario(Color.BLANCO).conTela(Material.SATEN).crearPrenda();
	Prenda ojotas = new PrendaBuilder().conTipo(TipoPrenda.Ojotas).conTela(Material.CAUCHO).conColorPrimario(Color.NEGRO).crearPrenda();
	Prenda jean = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.AZUL).crearPrenda();
	Guardarropa armario = new Guardarropa();
	Evento eventoConFrecuenciaUnica = new Evento(sugeridor, new FrecuenciaUnicaVez(2019,2,16),"Sin descripcion");//Fecha "16-02-2019" -> Es decir, un evento finalizado

	
	@Before
	public void setUp(){
		juan.cargarPrenda(armario, camisaCorta);
		juan.cargarPrenda(armario, zapatos);
		juan.cargarPrenda(armario, gorra);
		juan.cargarPrenda(armario, camisaLarga);
		juan.cargarPrenda(armario, ojotas);
		juan.agregarGuardarropa(armario);
		em.persist(juan);
		em.persist(eventoConFrecuenciaUnica);
	}
	
	@Test
	public void siSePersistenDosGuardarropasDistintosTendranDosIDsDistintos() throws Exception {
		Guardarropa otroArmario = new Guardarropa();
		juan.agregarGuardarropa(otroArmario);
		/*
		Set<Guardarropa> guardarropas = em
				  .createQuery("from Guardarropa order by Id", Guardarropa.class)
				  .getResultList().stream().collect(Collectors.toSet());*/
		Guardarropa armarioQuery = em
				  .createQuery("from Guardarropa order by Id", Guardarropa.class)
				  .getResultList()
				  .get(0);
		
		Guardarropa otroArmarioQuery = em
				  .createQuery("from Guardarropa order by Id", Guardarropa.class)
				  .getResultList()
				  .get(1);
		
		assertNotEquals(armarioQuery.getId(),otroArmarioQuery.getId());
	}
	
	@Test
	public void siSeSolicitanAtuendosSePersisten() {

		 juan.cargarPrenda(armario, jean);

		 Set<Prenda> prendasQuery = em
				  .createQuery("from Guardarropa order by Id", Guardarropa.class)
				  .getResultList()
				  .get(0).prendas();
		 /*
		 Set<Guardarropa> guardarropas = em
				  .createQuery("from Guardarropa order by Id", Guardarropa.class)
				  .getResultList().stream().collect(Collectors.toSet());*/
		 
		 assertTrue(prendasQuery.contains(jean)); 
	}
	
	@Test
	public void siSeSugiereConArmarioJuanTieneSugerencias() {
		juan.cargarPrenda(armario, jean);
		sugeridor.sugerirPrendasPara(juan).forEach(atuendo -> juan.agregarSugerencia(new Sugerencia(atuendo,eventoConFrecuenciaUnica)));
		Usuario usuarioQuery = em
				.createQuery("from Usuario", Usuario.class)
				.getResultList()
				.get(0);

		assertFalse(usuarioQuery.getSugerencias().isEmpty());
	}
	
	public void sugerirMasAceptarTodasLasSugerencias(Usuario usuario, Evento evento) {
		evento.sugerir(usuario);
		usuario.getSugerencias().stream().forEach(sugerencia -> usuario.clasificarUnaSugerencia(sugerencia, TipoSugerencias.ACEPTADA));
	}
	
	public Set<Set<Prenda>> obtenerAtuendosDeUsuario(Usuario usuario) {
		return usuario.getSugerencias().stream().map(sugerencia -> sugerencia.getAtuendo()).collect(Collectors.toSet());
	}
	
	public boolean todaLaRopaEstaLimpia(Set<Set<Prenda>> atuendos) {
		return atuendos.stream().allMatch(atuendo -> atuendo.stream().allMatch(prenda -> !prenda.isUsada()));
	}
	
	@Test
	public void siSeSugiereAtuendoActualizaLaPrendaAUsada() {
		juan.cargarPrenda(armario, jean);
		this.sugerirMasAceptarTodasLasSugerencias(juan, eventoConFrecuenciaUnica);
		assertFalse(this.todaLaRopaEstaLimpia(this.obtenerAtuendosDeUsuario(juan)));
	}
	
	@Test
	public void alLavarLaRopaSeActualizaLaPrenda() {
		juan.cargarPrenda(armario, jean);
		this.sugerirMasAceptarTodasLasSugerencias(juan, eventoConFrecuenciaUnica);
		juan.lavarLaRopa();
		Usuario usuarioQuery = em
				.createQuery("from Usuario", Usuario.class)
				.getResultList()
				.get(0);
		assertTrue(this.todaLaRopaEstaLimpia(this.obtenerAtuendosDeUsuario(usuarioQuery)));
	}
	
	
}
