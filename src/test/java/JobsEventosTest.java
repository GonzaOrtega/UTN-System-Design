import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;

public class JobsEventosTest {
	//Test provisorio para asegurarse de que JobsEventos funciona para probarlo hay que
	//sacar el comentario el codigo a continuacion sacar el comentario en contador que esta
	//en evento y cambia TimeUnit.DAYS a TimeUnit.SECONDS en JobsEventos
	/*@Test
	public void prueba() {
		Usuario juan = new Usuario(TipoUsuario.PREMIUM,0);
		Guardarropa armario = new Guardarropa();
		Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.ALGODON).conColorPrimario(Color.ROJO).conColorSecundario(Color.AMARILLO).crearPrenda();
		Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).crearPrenda();
		Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.NEGRO).conTela(Material.ALGODON).crearPrenda();
		Prenda jean = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.AZUL).crearPrenda();
		juan.agregarGuardarropa(armario);
		juan.cargarPrenda(armario, camisaCorta);
		juan.cargarPrenda(armario, zapatos);
		juan.cargarPrenda(armario, gorra);
		juan.cargarPrenda(armario, jean);
		Evento evento = new Evento("27-05-2019",juan);
		RepositorioEventos.getInstance().agendar(evento);
		JobsEventos jobs= new JobsEventos();
		assertTrue(evento.contador ==0);
		jobs.run();
		assertTrue(evento.contador ==1);
		DateFormat formato = new SimpleDateFormat(Evento.getFotmatoDeFecha());
		String fechaActual = formato.format(new Date());
		assertTrue(RepositorioEventos.getInstance().getEventos().size()==1);
		assertTrue(RepositorioEventos.getInstance().proximos(fechaActual).size()==1);
		assertTrue(evento.esProximo(fechaActual)==true);
	}*/
}
