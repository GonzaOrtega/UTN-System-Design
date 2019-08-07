package ui;
import domain.*;
import java.awt.Color;
//import org.eclipse.swt.widgets.Table;
import org.uqbar.arena.layout.*;
import org.uqbar.arena.widgets.*;
import org.uqbar.arena.windows.*;
import java.time.*;
import org.uqbar.arena.widgets.tables.*;
//import org.uqbar.arena.widgets.tables.Table;

public class QueMePongoView extends MainWindow<QueMePongoModel> {

	public QueMePongoView(){
	    super(new QueMePongoModel());
	  }

	  public void createContents(Panel mainPanel){
		  	this.setTitle("Que Me Pongo1");
			new Label(mainPanel).setText("¡Bienvenido a Que Me Pongo!");
			new Label(mainPanel).setText("Ingrese dos fechas (AAAA,MM,DD) para buscar los eventos que se encuentran entre las mismsas");
			mainPanel.setLayout(new VerticalLayout());
			Panel panelHorizontal= new Panel(mainPanel).setLayout(new HorizontalLayout());
			new Label(panelHorizontal).setText("Ingrese una fecha:");
			new NumericField(panelHorizontal).setWidth(100).bindValueToProperty("fecha");
			new Label(panelHorizontal).setText("Ingrese otra fecha:");
			new NumericField(panelHorizontal).setWidth(100).bindValueToProperty("otraFecha");
			new Button(mainPanel).setCaption("Obtener eventos");
			Table<Evento> tabla = new Table<Evento>(mainPanel, Evento.class);
			new Column<Evento>(tabla).setTitle("Fecha").setFixedSize(150).bindContentsToProperty("fecha");
			new Column<Evento>(tabla).setTitle("Titulo").setFixedSize(300).alignCenter().bindContentsToProperty("titulo");
			new Column<Evento>(tabla).setTitle("¿Sugerencias listas?").setFixedSize(100).bindContentsToProperty("proximo");


	  }
	  
	  public String fecha(String unaFecha) {
		  int anio = Integer.parseInt(unaFecha)/10000;
		  int mes = (Integer.parseInt(unaFecha)%10000)/100;
		  int dia = (Integer.parseInt(unaFecha)%1000000);
		  return LocalDate.of(anio, mes, dia).toString();
	  }
	  
	  public static void main(String[] args) {
		    new QueMePongoView().startApplication();
	  }

	  
}