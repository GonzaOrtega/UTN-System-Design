package tiempo.checkboxes;

import java.util.HashMap;

import domain.frecuenciasDeEventos.FrecuenciaAnual;
import domain.frecuenciasDeEventos.FrecuenciaDeEvento;
import domain.frecuenciasDeEventos.FrecuenciaDiaria;
import spark.Request;

public class AnioCheckbox implements Tiempo{
	public Integer mes;
	public Integer dia;
	
	public FrecuenciaDeEvento obtenerFrecuencia() {
		return new FrecuenciaAnual(mes, dia);
	}
	
	public boolean verificarTiempo(String tiempo) {
		return tiempo.equals("Anual");
	}
	
	public String esPeriodico() {
		return "esAnual";
	}

	public boolean datosIngresadosCorrectamente(Request req) {
		String mesString = req.queryParams("mes");
		String diaString = req.queryParams("dia");
		mes = Integer.parseInt(mesString);
		dia = Integer.parseInt(diaString);
		return validarFecha();
	}

	public boolean validarFecha() {
		return validarMes() && validarDias();
	}


	private boolean validarDias() {
		return dia>=1 && dia<=30;
	}

	private boolean validarMes() {
		return mes >=1 && mes <=12;
	}

	
//	public void vincularWeb(Request req, HashMap<String, Object> viewModel) {
//		String mesString = req.queryParams("mes");
//		String diaString = req.queryParams("dia");
//		viewModel.put("mes", mesString);
//		viewModel.put("dia", diaString);
//		if(!this.seIngresoFecha(mesString, diaString)) {
//			noRecibioFechaPorAhora();
//		}else {
//			try {
//				mes = Integer.parseInt(mesString);
//				dia = Integer.parseInt(diaString);
//				if(!this.validarFecha())
//					setError(viewModel);
//			}catch(Exception e){
//				setError(viewModel);
//			}
//		}
//	}
//	
//	public boolean seIngresoFecha(String mesString, String diaString) {
//		return mesString != null && diaString != null;
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
