package tiempo.checkboxes;

import java.util.HashMap;

import domain.frecuenciasDeEventos.FrecuenciaDeEvento;
import domain.frecuenciasDeEventos.FrecuenciaDiaria;
import spark.Request;

public class DiaCheckbox implements Tiempo{
	public Integer hora;
	
	public String esPeriodico() {
		return "esDiaria";
	}

	public boolean verificarTiempo(String tiempo) {
		return tiempo.equals("Diaria");
	}

	public FrecuenciaDeEvento obtenerFrecuencia() {
		return new FrecuenciaDiaria(hora);
	}
	
	public boolean datosIngresadosCorrectamente(Request req) {
		String horaString = req.queryParams("hora");
		hora = Integer.parseInt(horaString); // Que sea entero ya esta validado por HTML
		return validarFecha();
	}
	
	public boolean validarFecha() {
		return validarHora();
	}
	
	private boolean validarHora() {
		return hora >=0 && hora <=24;
	}
	
//	public void vincularWeb(Request req, HashMap<String, Object> viewModel) {
//		String horaString = req.queryParams("hora");
//		viewModel.put("hora", horaString);
//		if(horaString == null) {
//			noRecibioFechaPorAhora();
//		}else {
//			try {
//				hora = Integer.parseInt(horaString);
//				if(!this.validarFecha())
//					setError(viewModel);
//			}catch(Exception e){
//				setError(viewModel);
//			}
//		}
//	}
//	
//	
//	public void noRecibioFechaPorAhora() {
//		error = true;
//	}
//	
//	public void setError(HashMap<String, Object> viewModel) {
//		error = true;
//		viewModel.put("fechaIncorrecta", true);
//	}

	
}
