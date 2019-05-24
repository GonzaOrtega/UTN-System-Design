import java.util.List;
import java.util.ArrayList;

public class RepositorioSugerencias {
	List<SugerenciaParaEvento> sugerencias = new ArrayList<SugerenciaParaEvento>();
	
	
	public void guardarSugerencia(SugerenciaParaEvento sugerencia){
		this.sugerencias.add(sugerencia);
	}
	
	public SugerenciaParaEvento deshacerUltimaOperacion() {
		int ultimoIndice = this.sugerencias.size()-1;
		SugerenciaParaEvento ultimaSugerencia = this.sugerencias.get(ultimoIndice);
		sugerencias.remove(ultimoIndice);
		return ultimaSugerencia;
	}
	
}
