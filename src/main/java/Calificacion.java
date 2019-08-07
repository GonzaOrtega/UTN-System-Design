import enums.Categoria;
import enums.TipoSensaciones;

public class Calificacion {
	  private Categoria  parteCuerpo;
	  private TipoSensaciones sensacion; 
	  public Calificacion(Categoria x, TipoSensaciones y) { 
	    this.parteCuerpo = x; 
	    this.sensacion = y; 
	  }
	  public Categoria getCategoria() {
		  return this.parteCuerpo;
	  }
	  public TipoSensaciones getSensacion() {
		  return this.sensacion;
	  }
}
