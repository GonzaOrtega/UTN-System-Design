package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import org.junit.Before;

import domain.apisClima.MockAPI;
import domain.apisClima.OpenWeatherMapAPI;
import domain.apisClima.ProveedorClima;
import domain.enums.Color;
import domain.enums.Material;
import domain.enums.TipoPrenda;
import domain.enums.TipoUsuario;
import domain.frecuenciasDeEventos.FrecuenciaUnicaVez;

public class main {

	public static void main(String[] args) {
		MockAPI APIDeMentiritas = new MockAPI(21,23,false);
		Sugeridor sugeridor = new Sugeridor(APIDeMentiritas);
		Usuario juan = new Usuario(TipoUsuario.PREMIUM, 0);
		Guardarropa armario = new Guardarropa();
		Guardarropa otroArmario = new Guardarropa();
		Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.ALGODON).conColorPrimario(Color.ROJO).conColorSecundario(Color.AMARILLO).conAbrigo(0).crearPrenda();
		Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).conAbrigo(1).crearPrenda();
		Prenda ojotas =  new PrendaBuilder().conTipo(TipoPrenda.Ojotas).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).conAbrigo(-1).crearPrenda();
		Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.NEGRO).conTela(Material.ALGODON).conAbrigo(0).crearPrenda();
		Prenda jean = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.AZUL).conAbrigo(1).crearPrenda();
		Prenda camperaGucci = new PrendaBuilder().conTipo(TipoPrenda.Campera).conTela(Material.ALGODON).conColorPrimario(Color.NEGRO).conAbrigo(2).crearPrenda();
		Prenda botas = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).conAbrigo(3).crearPrenda();
		Prenda pantalonAbrigo = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.AZUL).conAbrigo(3).crearPrenda();
		Prenda buzo =  new PrendaBuilder().conTipo(TipoPrenda.Buzo).conTela(Material.ALGODON).conColorPrimario(Color.ROSA).conColorSecundario(Color.AMARILLO).conAbrigo(2).crearPrenda();
		LocalDateTime dia = LocalDateTime.of(LocalDate.of(2019, Month.MAY, 17),LocalTime.now());
		Evento eventoProximo = new Evento(sugeridor,new FrecuenciaUnicaVez(2019,5,24),"Sin descripcion");//"24-05-2019"
		Evento otroEventoProximo = new Evento(sugeridor,new FrecuenciaUnicaVez(2019,5,23),"Sin descripcion");//"24-05-2019"

			juan.agregarGuardarropa(armario);
			juan.agregarGuardarropa(otroArmario);
			juan.agendarEvento(otroEventoProximo);
			juan.agendarEvento(eventoProximo);
			juan.cargarPrenda(armario, jean);
			juan.cargarPrenda(armario, camisaCorta);
			juan.cargarPrenda(armario,zapatos);
			juan.cargarPrenda(armario,gorra);
			juan.cargarPrenda(armario,camperaGucci);
			juan.cargarPrenda(armario, buzo);
			juan.cargarPrenda(armario,botas);
			juan.cargarPrenda(armario,pantalonAbrigo);
			
		System.out.println("\nJuan:\n - Sugerencias:"+juan.getSugerencias()+"\n — Eventos:"+juan.eventos()+"\n — Eventos sugeridos:"+juan.eventosSugeridos());
		System.out.println("\nSe procede a iniciar job...");
		RepositorioDeUsuarios.getInstance().notificarAUsuariosAfectadosPorCambioDeClima();;
		RepositorioDeUsuarios.getInstance().obtenerSugerenciasDeEventosProximosA(dia);
		System.out.println("\nJuan:\n - Sugerencias:"+juan.getSugerencias()+"\n — Eventos:"+juan.eventos()+"\n— Eventos sugeridos:"+juan.eventosSugeridos());
		System.out.println("\n¿Juan tiene sugerencias de "+ eventoProximo+"?:"+juan.tengoSugerenciaDeEsteEvento(eventoProximo));
		System.out.println("Los atuendos de las sugerencias son:"+juan.getSugerencias().stream().map(s->s.getAtuendo()).collect(Collectors.toSet()));
		System.out.println("\nLos atuendos del evento "+ eventoProximo +" son:\n"+ juan.atuendosSugeridosDe(eventoProximo));
		System.out.println("Cambio de temperatura...");
		APIDeMentiritas.temperatura(3);
		System.out.println("\nSe procede a iniciar job...");
		RepositorioDeUsuarios.getInstance().notificarAUsuariosAfectadosPorCambioDeClima();;
		RepositorioDeUsuarios.getInstance().obtenerSugerenciasDeEventosProximosA(dia);
		System.out.println("————————————————————————————————————————————");

		


	}

}
