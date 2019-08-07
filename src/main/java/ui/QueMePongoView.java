package ui;
import domain.*;
import org.uqbar.arena.layout.*;
import org.uqbar.arena.widgets.*;
import org.uqbar.arena.windows.*;
import org.uqbar.arena.widgets.tables.*;

public class QueMePongoView extends MainWindow<QueMePongoModel> {

	public QueMePongoView(){
	    super(new QueMePongoModel());
	  }

	  public void createContents(Panel mainPanel){
		  	this.setTitle("Que Me Pongo1");
			new Label(mainPanel).setText("Â¡Bienvenido a Que Me Pongo!");
			new Label(mainPanel).setText("Ingrese dos fechas (AAAA,MM,DD) para buscar los eventos que se encuentran entre las mismsas");
			mainPanel.setLayout(new VerticalLayout());
			Panel panelHorizontal= new Panel(mainPanel).setLayout(new HorizontalLayout());
			new Label(panelHorizontal).setText("Ingrese una fecha inicial:");
			new NumericField(panelHorizontal).setWidth(100).bindValueToProperty("fechaInicio");
			new Label(panelHorizontal).setText("Ingrese otra fecha final:");
			new NumericField(panelHorizontal).setWidth(100).bindValueToProperty("fechaFin");
			new Button(mainPanel).setCaption("Obtener eventos").onClick(()-> getModelObject().listarEventos());
			Table<Evento> tabla = new Table<Evento>(mainPanel, Evento.class);
			tabla.bindItemsToProperty("eventos");
			//tabla.bindValueToProperty("eventos");
			new Column<Evento>(tabla).setTitle("Fecha").setFixedSize(150).bindContentsToProperty("fecha");
			//new Column<Evento>(tabla).setTitle("Titulo").setFixedSize(300).alignCenter().bindContentsToProperty("titulo");
			//new Column<Evento>(tabla).setTitle("¿Sugerencias listas?").setFixedSize(100).bindContentsToProperty("proximo");
			//new Column<Evento>(tabla).setTitle("Repeticion").setFixedSize(100).bindContentsToProperty("frecuencia");
	  }
	
	  
	  public static void main(String[] args) {
		    new QueMePongoView().startApplication();
	  }

	  
}