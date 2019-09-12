package domain;

import domain.enums.Categoria;
import domain.enums.TipoSensaciones;

public class Calificacion {

	private Categoria parteCuerpo;
	private TipoSensaciones sensacion;

	public Calificacion(Categoria parteCuerpo, TipoSensaciones sensacion) {
		this.parteCuerpo = parteCuerpo;
		this.sensacion = sensacion;
	}

	public Categoria getCategoria() {
		return this.parteCuerpo;
	}

	public TipoSensaciones getSensacion() {
		return this.sensacion;
	}
}
