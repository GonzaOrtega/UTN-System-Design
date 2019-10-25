package controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import domain.Evento;
import domain.Prenda;
import domain.PrendaBuilder;
import domain.RepositorioDeUsuarios;
import domain.Sugerencia;
import domain.Usuario;
import domain.enums.Color;
import domain.enums.Material;
import domain.enums.TipoFrecuencia;
import domain.enums.TipoPrenda;
import domain.enums.TipoSugerencias;
import domain.frecuenciasDeEventos.FrecuenciaUnicaVez;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class CalendarioController implements WithGlobalEntityManager, TransactionalOps{
	List<Evento> eventosPendientes=null; 
	public ModelAndView verSugerencia(Request req, Response res) {
		String numString=req.queryParams("eventoNum");
		int numEvento = Integer.parseInt(numString);
		Evento evento = eventosPendientes.get(numEvento);
		res.cookie("evento",evento.getId().toString());
		res.redirect("/sugerenciasPendientes");
		return null;
	}
	public String verCalendario(Request req, Response res) {
		String dia = req.queryParams("dia");
		String mes = req.queryParams("mes");
		String anio = req.queryParams("anio");
		Boolean hayFecha = isNumeric(dia) && isNumeric(mes) && isNumeric(anio);
		Usuario usuarie = RepositorioDeUsuarios.getInstance().buscarPorNombre(req.cookie("nombreUsuario"));
		List<Evento>eventoList = null;
		List<Evento>eventosNoPendientes = null;
		eventoList = hayFecha? calcularEventos(dia,mes,anio,usuarie):null;
		
		if(eventoList!=null) {
		 eventosPendientes= tieneSugerenciasPendientes(eventoList,usuarie);
		 eventosNoPendientes= eventoList;
		 eventosNoPendientes.removeAll(eventosPendientes);
		}else {
			eventosPendientes = null;
			eventosNoPendientes=null;
		}
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("eventosPendientes", eventosPendientes);
		viewModel.put("eventosNoPendientes", eventosNoPendientes);
		viewModel.put("eventos",eventoList);
		viewModel.put("hayFecha", hayFecha);
		ModelAndView modelAndView = new ModelAndView(viewModel, "calendario.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
	public List<Evento> tieneSugerenciasPendientes(List<Evento> eventos,Usuario usuario) {
		List<Sugerencia> sugerencias =usuario.getSugerencias();
		sugerencias.remove(null);
		if(!sugerencias.stream().anyMatch(sugerencia->sugerencia.getEstado().equals(TipoSugerencias.PENDIENTE)))
		return null;
			return eventos.stream().filter(evento->sugerencias.stream().anyMatch(sugerencia->sugerencia.getEvento().equals(evento))).collect(Collectors.toList());
	}
	public static boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}
	
	private List<Evento> calcularEventos(String dia,String mes,String anio,Usuario usuarie){
		int diaNum = Integer.parseInt(dia);
		int mesNum = Integer.parseInt(mes);
		int anioNum = Integer.parseInt(anio);
		List<Evento> eventos= new ArrayList<Evento>(usuarie.eventos());
		LocalDateTime fecha = LocalDateTime.of(anioNum,mesNum,diaNum,0,0,0);
		//agregarEvento(usuarie);
		//agregarSugerencia(usuarie);
		return eventos.stream()
				.filter(evento->((evento.getFrecuencia().equals(TipoFrecuencia.Unico)) && sucedeEnEsteDia(fecha,evento)))
				.collect(Collectors.toList()); 
	}
	private static boolean sucedeEnEsteDia(LocalDateTime fecha,Evento evento) {
		LocalDateTime fechaEvento=evento.cualEsLaFechaProxima(fecha);
		return fechaEvento.equals(fecha)||(fechaEvento.isAfter(fecha) && fechaEvento.isBefore(fecha.plusDays(1)));
	}
	//////////////////////////////////////////SETEA_EJEMPLOS///////////////////////////////////////////////////////////////
	private void agregarEvento(Usuario usuario) {
		withTransaction(() -> {
			FrecuenciaUnicaVez frecuencia =new FrecuenciaUnicaVez(2019,5,24);
			Evento evento = new Evento(frecuencia,"Sin descripcion");
			usuario.agendarEvento(evento);
			entityManager().persist(frecuencia);
			entityManager().persist(evento);
			entityManager().getTransaction().commit();
		});
		entityManager().close();
	}
	private void agregarSugerencia(Usuario usuario) {
		withTransaction(()->{
			FrecuenciaUnicaVez frecuencia =new FrecuenciaUnicaVez(2019,5,24);
			Evento evento = new Evento(frecuencia,"TengoUnaSugerencia");
			Set<Prenda> atuendo = new HashSet<Prenda>();
			Set<Prenda> atuendo2 = new HashSet<Prenda>();
			Prenda jean = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.Jean).conColorPrimario(Color.Azul).crearPrenda();
			Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.Algodon).conColorPrimario(Color.Rojo).conColorSecundario(Color.Amarillo).crearPrenda();
			atuendo.add(jean);
			atuendo.add(camisaCorta);
			atuendo2.add(jean);
			Sugerencia sugerencia1 = new Sugerencia(atuendo,evento);
			Sugerencia sugerencia2 = new Sugerencia(atuendo2,evento);
			usuario.agregarSugerencia(sugerencia1);
			usuario.agregarSugerencia(sugerencia2);
			usuario.agendarEvento(evento);
			entityManager().persist(frecuencia);
			entityManager().persist(evento);
			entityManager().persist(sugerencia2);
			entityManager().persist(sugerencia1);
			entityManager().persist(jean);
			entityManager().persist(camisaCorta);
		});
		entityManager().close();
	}
}
