package controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import domain.*;
import domain.RepositorioDeUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class Calendario2Controller {
	public ModelAndView test(Request req, Response res) {
		String user = "juan";
		Usuario usuario = RepositorioDeUsuarios.getInstance().buscarPorNombre(user);
		Set<Evento> listaEventos=usuario.eventosProximos(LocalDateTime.of(
								LocalDate.now(),
								LocalTime.now())
								);
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("listaEventos",listaEventos);
		ModelAndView modelAndView = new ModelAndView(viewModel, "calendar.hbs");
		
		return modelAndView;
	}
	
}
