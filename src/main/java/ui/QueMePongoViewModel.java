package ui;
import domain.*;

import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.*;
import org.uqbar.arena.windows.*;
import org.uqbar.commons.model.annotations.Observable;
import java.time.*;


@Observable
public class QueMePongoViewModel{
	private QueMePongoModel model;
	private LocalDateTime fecha;
	private LocalDateTime otraFecha;

	
  	public QueMePongoViewModel(QueMePongoModel unModel) {
  		this.model = unModel;
  	}

  	// Getters y setters
  	
	public QueMePongoModel getModel() {
		return model;
	}

	public void setModel(QueMePongoModel model) {
		this.model = model;
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public LocalDateTime getOtraFecha() {
		return otraFecha;
	}

	public void setOtraFecha(LocalDateTime otraFecha) {
		this.otraFecha = otraFecha;
	}

	/*public void listarEventos() {
		model.eventos().forEach(e -> new Label(mainPanel).bindEnabledToProperty("evento"));
	}*/
	
	public void listarEn(Panel mainPanel) {
		new Label(mainPanel).setText("HOLA MANOLA");

	}
  	

}