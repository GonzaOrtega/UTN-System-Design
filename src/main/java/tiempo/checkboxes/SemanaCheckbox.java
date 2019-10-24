package tiempo.checkboxes;

import java.util.HashMap;

import domain.frecuenciasDeEventos.FrecuenciaDeEvento;
import domain.frecuenciasDeEventos.FrecuenciaMensual;
import domain.frecuenciasDeEventos.FrecuenciaSemanal;
import spark.Request;

public class SemanaCheckbox implements Tiempo{
	public Integer dia = 1; // Establezco hora default
	boolean esSemanal = true;
	boolean error = false;
	
	public FrecuenciaDeEvento obtenerFrecuencia(Request req, HashMap<String, Object> viewModel) {
		this.vincularWeb(req, viewModel);
		if(!error) {
			return new FrecuenciaSemanal(dia);
		}
		return null;
	}
	
	public boolean verificarTiempo(String tiempo) {
		return tiempo.equals("Semanal");
	}
	
	public void esPeriodico(HashMap<String, Object> viewModel) {
		viewModel.put("esSemanal", esSemanal);
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
		return dia >=1 && dia <=7;
	}
	
	public void noRecibioFechaPorAhora() {
		error = true;
	}
	
	public void setError(HashMap<String, Object> viewModel) {
		error = true;
		viewModel.put("fechaIncorrecta", true);
	}

}
