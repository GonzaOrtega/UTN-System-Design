package tiempo.checkboxes;

import java.util.HashMap;

import domain.frecuenciasDeEventos.FrecuenciaDeEvento;
import spark.Request;

public interface Tiempo {
	public boolean verificarTiempo(String tiempo);
	public void esPeriodico(HashMap<String, Object> viewModel);
	public FrecuenciaDeEvento obtenerFrecuencia(Request req, HashMap<String, Object> viewModel);
}
