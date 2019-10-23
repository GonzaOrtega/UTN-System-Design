package server.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import domain.Evento;
import domain.RepositorioDeUsuarios;
import domain.Sugerencia;
import domain.Usuario;
import domain.enums.TipoFrecuencia;
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
		eventoList = hayFecha? calcularEventos(dia,mes,anio,usuarie):null;
		HashMap<String, Object> viewModel = new HashMap<>();
		List<Sugerencia> haySugerenciasPendientes = tieneSugerenciasPendientes(eventoList,usuarie);
		viewModel.put("haySugerencias", haySugerenciasPendientes);
		viewModel.put("eventos", eventoList);
		viewModel.put("hayFecha", hayFecha);
		ModelAndView modelAndView = new ModelAndView(viewModel, "calendario.hbs");
		eventos = eventoList;
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
	public boolean tieneSugerenciaPendiente(List<Evento> eventos,Sugerencia sugerencia) {
		return eventos.stream().anyMatch(evento->evento==sugerencia.getEvento())
				&&sugerencia.getEstado()==TipoSugerencias.PENDIENTE;
	}
	public List<Sugerencia> tieneSugerenciasPendientes(List<Evento> eventos,Usuario usuario) {
		List<Sugerencia> sugerencias =usuario.getSugerencias();
		return sugerencias.stream().filter(sugerencia->tieneSugerenciaPendiente(eventos,sugerencia))
				.collect(Collectors.toList());
	}
	public List<Evento> agregarEventos(Set<Evento> sets){
		List<Evento> list = new ArrayList<Evento>();
		sets.stream().map(set->list.add(set));
		return list;
	}
	public static boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}
	public boolean noTieneSugerenciaPendiente(Evento evento,Usuario usuario) {
		return !usuario.getSugerencias().stream().anyMatch(sugerencia->sugerencia.getEvento()==evento);
	}
	private List<Evento> calcularEventos(String dia,String mes,String anio,Usuario usuarie){
		int diaNum = Integer.parseInt(dia);
		int mesNum = Integer.parseInt(mes);
		int anioNum = Integer.parseInt(anio);
		List<Evento> eventos= new ArrayList<Evento>(usuarie.eventos());
		LocalDateTime fecha = LocalDateTime.of(anioNum,mesNum,diaNum,0,0,0);
		eventos = eventos.stream().filter(evento->noTieneSugerenciaPendiente(evento,usuarie)).collect(Collectors.toList());
		//agregarEvento(usuarie);
		return eventos.stream()
				.filter(evento->((evento.getFrecuencia() ==TipoFrecuencia.Unico) && sucedeEnEsteDia(fecha,evento)))
				.collect(Collectors.toList()); 
	}
	private void agregarEvento(Usuario usuario) {
		em.getTransaction().begin();
			FrecuenciaUnicaVez frecuencia =new FrecuenciaUnicaVez(2019,5,24);
			Evento evento = new Evento(frecuencia,"Sin descripcion");
			usuario.agendarEvento(evento);
			em.persist(frecuencia);
			em.persist(evento);
			//em.persist(usuario);
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
