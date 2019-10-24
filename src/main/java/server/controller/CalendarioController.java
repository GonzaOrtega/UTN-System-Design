package server.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
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

public class CalendarioController implements WithGlobalEntityManager{
	EntityManager em = entityManager();
	List<Evento> eventos=null; 
	public ModelAndView verSugerencia(Request req, Response res) {
		String numString=req.queryParams("evento");
		int numEvento = Integer.parseInt(numString);
		Evento evento = eventos.get(numEvento);
		res.cookie("evento",evento.getId().toString());
		res.redirect("/sugerencias");
		return null;
	}
	public String verCalendario(Request req, Response res) {
		String dia = req.queryParams("dia");
		String mes = req.queryParams("mes");
		String anio = req.queryParams("anio");
		Boolean hayFecha = isNumeric(dia) && isNumeric(mes) && isNumeric(anio);
		Usuario usuarie = RepositorioDeUsuarios.getInstance().buscarPorNombre(req.cookie("nombreUsuario"));
		List<Evento>eventoList = null;
		List<Evento>eventoNoPendientes = null;
		eventoList = hayFecha? calcularEventos(dia,mes,anio,usuarie):null;
		HashMap<String, Object> viewModel = new HashMap<>();
		List<Sugerencia> haySugerenciasPendientes = tieneSugerenciasPendientes(eventoList,usuarie);
		eventoNoPendientes = eventosNoPendientes(eventoList,usuarie);
		viewModel.put("haySugerencias", haySugerenciasPendientes);
		viewModel.put("eventos", eventoNoPendientes);
		viewModel.put("hayFecha", hayFecha);
		ModelAndView modelAndView = new ModelAndView(viewModel, "calendario.hbs");
		eventos = eventoList;
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
	public boolean tieneAsignadoUnEvento(List<Evento> eventos,Sugerencia sugerencia) {
		return (eventos.stream().anyMatch(evento->evento.equals(sugerencia.getEvento()))
				&&sugerencia.getEstado().equals(TipoSugerencias.PENDIENTE));
	}
	public List<Sugerencia> tieneSugerenciasPendientes(List<Evento> eventos,Usuario usuario) {
		List<Sugerencia> sugerencias =usuario.getSugerencias();
		sugerencias.remove(null);
		return sugerencias.stream().filter(sugerencia->tieneAsignadoUnEvento(eventos,sugerencia))
				.collect(Collectors.toList());
	}
	
	/*public List<Evento> agregarEventos(Set<Evento> sets){
		List<Evento> list = new ArrayList<Evento>();
		sets.stream().map(set->list.add(set));
		return list;
	}*/
	public static boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}
	public boolean noTieneSugerenciaPendiente(Evento evento,Usuario usuario) {	
		List<Sugerencia> sugerencias =usuario.getSugerencias();
		sugerencias.remove(null);
		return !sugerencias.stream()
				.anyMatch(sugerencia->sugerencia.getEvento().equals(evento)&&sugerencia.getEstado().equals(TipoSugerencias.PENDIENTE));
	}
	private List<Evento> eventosNoPendientes(List<Evento> eventos,Usuario usuario) {	
            List<Evento>resp= eventos.stream().filter(evento->noTieneSugerenciaPendiente(evento,usuario)).collect(Collectors.toList());
            System.out.println(resp);
            return resp;
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
	private void agregarEvento(Usuario usuario) {
		em.getTransaction().begin();
			FrecuenciaUnicaVez frecuencia =new FrecuenciaUnicaVez(2019,5,24);
			Evento evento = new Evento(frecuencia,"Sin descripcion");
			usuario.agendarEvento(evento);
			em.persist(frecuencia);
			em.persist(evento);
		em.getTransaction().commit();
	}
	private void agregarSugerencia(Usuario usuario) {
		em.getTransaction().begin();
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
			em.persist(frecuencia);
			em.persist(evento);
			em.persist(sugerencia2);
			em.persist(sugerencia1);
			em.persist(jean);
			em.persist(camisaCorta);
		em.getTransaction().commit();
	}
	private static boolean sucedeEnEsteDia(LocalDateTime fecha,Evento evento) {
		LocalDateTime fechaEvento=evento.cualEsLaFechaProxima(fecha);
		return fechaEvento.equals(fecha)||(fechaEvento.isAfter(fecha) && fechaEvento.isBefore(fecha.plusDays(1)));
	}
	public static String verSugerencias(Request req,Response res) {
		res.redirect("/sugerencias");
		return null;
	}
}
