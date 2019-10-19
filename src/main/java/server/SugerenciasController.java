package server;

import java.util.*;
import java.util.stream.Collectors;

import domain.Evento;
import domain.Guardarropa;
import domain.Prenda;
import domain.PrendaBuilder;
import domain.Sugerencia;
import domain.Usuario;
import domain.apisClima.MockAPI;
import domain.apisClima.OpenWeatherMapAPI;
import domain.apisClima.ProveedorClima;
import domain.enums.Color;
import domain.enums.Material;
import domain.enums.TipoPrenda;
import domain.enums.TipoSugerencias;
import domain.enums.TipoUsuario;
import domain.frecuenciasDeEventos.FrecuenciaUnicaVez;
import spark.Request;
import spark.Response;

public class SugerenciasController {
	public static String verSugerenciasAceptadas(Request req, Response res) {
		return "Hola!!";
	}
	
	public static String calificarSugerencias(Request req, Response res) {
		return "Hola!!";
	}
	
	// Hardcodeo esto para probarlo, luego se hace con las sugerencias del usuario que inicia sesion
	public List<Sugerencia> generarYObtenerSugerenciasAceptadas() {
		Usuario juan = this.iniciarUsuario();
		Evento eventoConFrecuenciaUnica = new Evento(new FrecuenciaUnicaVez(2019,2,16),"Sin descripcion");//Fecha "16-02-2019" -> Es decir, un evento finalizado
		return this.sugerirMasAceptarTodasLasSugerencias(juan, eventoConFrecuenciaUnica);
	}
	
	public Usuario iniciarUsuario() {
		ProveedorClima weatherAPI = new OpenWeatherMapAPI();
		ProveedorClima APIDeMentiritas = new MockAPI(21,23,false);
		Usuario juan = new Usuario(TipoUsuario.PREMIUM,0,"juan","123");
		Guardarropa armario = new Guardarropa();
		Guardarropa otroArmario = new Guardarropa();
		Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.ALGODON).conColorPrimario(Color.ROJO).conColorSecundario(Color.AMARILLO).crearPrenda();
		Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).crearPrenda();
		Prenda zapatillas = new PrendaBuilder().conTipo(TipoPrenda.Zapatillas).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).crearPrenda();
		Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.NEGRO).conTela(Material.ALGODON).crearPrenda();
		Prenda sombrero= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.NEGRO).conTela(Material.ALGODON).crearPrenda();
		Prenda camisaLarga = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaLarga).conColorPrimario(Color.BLANCO).conTela(Material.SATEN).crearPrenda();
		Prenda camisaDeLara = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaLarga).conColorPrimario(Color.BLANCO).conTela(Material.SATEN).crearPrenda();
		Prenda ojotas = new PrendaBuilder().conTipo(TipoPrenda.Ojotas).conTela(Material.CAUCHO).conColorPrimario(Color.NEGRO).crearPrenda();
		Prenda jean = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.AZUL).crearPrenda();
		Prenda pantalon = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.BLANCO).crearPrenda();
		
		
		juan.agregarGuardarropa(armario);
		juan.cargarPrenda(armario, camisaCorta);
		juan.cargarPrenda(armario, zapatos);
		juan.cargarPrenda(armario, gorra);
		juan.cargarPrenda(armario, camisaLarga);
		juan.cargarPrenda(armario, ojotas);
		juan.cargarPrenda(armario, jean);
		
		return juan;
	}
	
	public List<Sugerencia> sugerirMasAceptarTodasLasSugerencias(Usuario usuario, Evento evento) {
		evento.sugerir(usuario);
		usuario.getSugerencias().stream().forEach(sugerencia -> usuario.clasificarUnaSugerencia(sugerencia, TipoSugerencias.ACEPTADA));
		return usuario.getSugerencias();
	}
}
