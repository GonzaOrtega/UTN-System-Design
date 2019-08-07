package ui;
import domain.*;
import scala.Product;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.*;
import org.uqbar.arena.windows.*;
import org.uqbar.commons.model.annotations.Observable;
import java.time.*;


@Observable
public class QueMePongoViewModel{
	private QueMePongoModel model;
	private LocalDate fecha;
	private LocalDate otraFecha;

	
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
	
	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalDate getOtraFecha() {
		return otraFecha;
	}

	public void setOtraFecha(LocalDate otraFecha) {
		this.otraFecha = otraFecha;
	}

	public void listarEventos() {
	}
	/*
	public void listarEn(Panel mainPanel) {
		
		
		List<Product> eventos = new List<Producto>(mainPanel);
		lstProducts.bindItemsToProperty("products");
		lstProducts.bindValueToProperty("selectedProduct");
		lstProducts.setWidth(220);
		lstProducts.setHeight(220);
	}*/
  	

}