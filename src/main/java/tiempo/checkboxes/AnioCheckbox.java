package tiempo.checkboxes;

import java.util.HashMap;

import domain.frecuenciasDeEventos.FrecuenciaAnual;
import domain.frecuenciasDeEventos.FrecuenciaDeEvento;
import domain.frecuenciasDeEventos.FrecuenciaDiaria;
import spark.Request;

public class AnioCheckbox implements Tiempo{
	public Integer mes = 1;
	public Integer dia = 1;
	boolean esAnual = true;
	boolean error = false;
	
	public FrecuenciaDeEvento obtenerFrecuencia(Request req, HashMap<String, Object> viewModel) {
		this.vincularWeb(req, viewModel);
		if(!error) {
			return new FrecuenciaAnual(mes, dia);
		}
		return null;
	}
	
	public boolean verificarTiempo(String tiempo) {
		return tiempo.equals("Anual");
	}
	
	public void esPeriodico(HashMap<String, Object> viewModel) {
		viewModel.put("esAnual", esAnual);
	}
	
	public void vincularWeb(Request req, HashMap<String, Object> viewModel) {
		String mesString = req.queryParams("mes");
		String diaString = req.queryParams("dia");
		viewModel.put("mes", mesString);
		viewModel.put("dia", diaString);
		if(!this.seIngresoFecha(mesString, diaString)) {
			noRecibioFechaPorAhora();
		}else {
			try {
				mes = Integer.parseInt(mesString);
				dia = Integer.parseInt(diaString);
				if(!this.validarFecha())
					setError(viewModel);
			}catch(Exception e){
				setError(viewModel);
			}
		}
	}
	
	public boolean seIngresoFecha(String mesString, String diaString) {
		return mesString != null && diaString != null;
	}
	
	public boolean validarFecha() {
		return (mes >=1 && mes <=12) && (dia>=1 && dia<=30);
	}
	
	public void noRecibioFechaPorAhora() {
		error = true;
	}
	
	public void setError(HashMap<String, Object> viewModel) {
		error = true;
		viewModel.put("fechaIncorrecta", true);
	}
}
