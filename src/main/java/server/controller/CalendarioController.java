package server.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import domain.Evento;
import domain.RepositorioDeUsuarios;
import domain.Usuario;
import domain.enums.TipoFrecuencia;
import domain.enums.TipoUsuario;
import domain.frecuenciasDeEventos.FrecuenciaUnicaVez;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class CalendarioController{
	public static String verCalendario(Request req, Response res) {
		String dia = req.queryParams("dia");
		String mes = req.queryParams("mes");
		String anio = req.queryParams("anio");
		Boolean hayFecha = dia !=null && mes!=null && anio!=null;
		
		Set<Evento> eventos = hayFecha? calcularEventos(dia,mes,anio):null;
		
		HashMap<String, Object> viewModel = new HashMap();
		viewModel.put("eventos", eventos);
		viewModel.put("hayFecha", hayFecha);
		ModelAndView modelAndView = new ModelAndView(viewModel, "calendario.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
	
	
	private static Set<Evento> calcularEventos(String dia,String mes,String anio){
		int diaNum = Integer.parseInt(dia);
		int mesNum = Integer.parseInt(mes);
		int anioNum = Integer.parseInt(anio);
		LocalDateTime fecha = LocalDateTime.of(anioNum,mesNum,diaNum,0,0,0);
		
	
		agregarEventoCualquiera();
		Set<Evento> eventos=RepositorioDeUsuarios.getInstance().eventos();
		return eventos.stream()
				.filter(evento->evento.getFrecuencia() ==TipoFrecuencia.Unico && sucedeEnEsteDia(fecha,evento))
				.collect(Collectors.toSet());
	}
	private static void agregarEventoCualquiera() {
		Usuario ana = new Usuario(TipoUsuario.PREMIUM,10,"ana","123");
		Evento eventoConFrecuenciaUnica = new Evento(new FrecuenciaUnicaVez(2019,2,16),"Sin descripcion");
		ana.agendarEvento(eventoConFrecuenciaUnica);
		RepositorioDeUsuarios.getInstance().agregar(ana);
	}
	private static boolean sucedeEnEsteDia(LocalDateTime fecha,Evento evento) {
		LocalDateTime fechaEvento=evento.cualEsLaFechaProxima(fecha);
		return fechaEvento.equals(fecha)||(fechaEvento.isAfter(fecha) && fechaEvento.isBefore(fecha.plusDays(1)));
	}
}
