package tiempo.checkboxes;

import java.util.HashMap;

import domain.frecuenciasDeEventos.FrecuenciaDeEvento;
import domain.frecuenciasDeEventos.FrecuenciaDiaria;
import domain.frecuenciasDeEventos.FrecuenciaMensual;
import spark.Request;

public class MesCheckbox implements Tiempo{
	public Integer dia = 1; // Establezco hora default
	boolean esMensual = true;
	boolean error = false;
	
	public FrecuenciaDeEvento obtenerFrecuencia(Request req, HashMap<String, Object> viewModel) {
		this.vincularWeb(req, viewModel);
		if(!error) {
			return new FrecuenciaMensual(dia);
		}
		return null;
	}
	
	public boolean verificarTiempo(String tiempo) {
		return tiempo.equals("Mensual");
	}
	
	public void esPeriodico(HashMap<String, Object> viewModel) {
		viewModel.put("esMensual", esMensual);
	}
	
	public void vincularWeb(Request req, HashMap<String, Object> viewModel) {
		String diaString = req.queryParams("dia");
		viewModel.put("dia", diaString);
		if(diaString == null) {
			noRecibioFechaPorAhora();
		}else {
			try {
				dia = Integer.parseInt(diaString);
				if(!this.validarFecha())
					setError(viewModel);
			}catch(Exception e){
				setError(viewModel);
			}
		}
	}
	
	public boolean validarFecha() {
		return dia >=1 && dia <=30;
	}
	
	public void noRecibioFechaPorAhora() {
		error = true;
	}
	
	public void setError(HashMap<String, Object> viewModel) {
		error = true;
		viewModel.put("fechaIncorrecta", true);
	}
	
}
