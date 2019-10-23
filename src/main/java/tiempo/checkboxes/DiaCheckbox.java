package tiempo.checkboxes;

import java.util.HashMap;

import domain.frecuenciasDeEventos.FrecuenciaDeEvento;
import domain.frecuenciasDeEventos.FrecuenciaDiaria;
import spark.Request;

public class DiaCheckbox implements Tiempo{
	public Integer hora = 1; // Establezco hora default
	boolean esDiario = true;
	
	public FrecuenciaDeEvento obtenerFrecuencia(Request req, HashMap<String, Object> viewModel) {
		this.vincularWeb(req, viewModel);
		if(!this.esError()) {
			return new FrecuenciaDiaria(hora);
		}
		return null;
	}
	
	public boolean verificarTiempo(String tiempo) {
		return tiempo == "Diaria";
	}
	
	public void esPeriodico(HashMap<String, Object> viewModel) {
		viewModel.put("esDiaria", esDiario);
	}
	
	public void vincularWeb(Request req, HashMap<String, Object> viewModel) {
		String horaString = req.queryParams("hora");
		viewModel.put("hora", horaString);
		if(horaString == null) {
			noRecibioFechaPorAhora();
		}else {
			try {
				hora = Integer.parseInt(horaString);
				if(!this.validarFecha())
					setError(viewModel);
			}catch(Exception e){
				setError(viewModel);
			}
		}
	}
	
	public boolean validarFecha() {
		return hora >=0 && hora <=24;
	}
	
	public boolean esError() {
		return hora == -1;
	}
	
	public void noRecibioFechaPorAhora() {
		hora = -1;
	}
	
	public void setError(HashMap<String, Object> viewModel) {
		hora = -1;
		viewModel.put("fechaIncorrecta", true);
	}
	
}
