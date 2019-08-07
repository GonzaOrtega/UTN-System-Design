package ui;
import domain.*;
import java.awt.Color;
import org.uqbar.arena.layout.*;
import org.uqbar.arena.widgets.*;
import org.uqbar.arena.windows.*;
import java.time.*;

public class QueMePongoView extends MainWindow<QueMePongoViewModel> {

	  public QueMePongoView(){
	    super(new QueMePongoViewModel(new QueMePongoModel()));
	  }

	  public void createContents(Panel mainPanel){
			//mainPanel.setLayout(new HorizontalLayout());
		  	this.setTitle("Que Me Pongo");
			new Label(mainPanel).setText("¡Bienvenido a Que Me Pongo!");
			Panel panelHorizontal= new Panel(mainPanel).setLayout(new HorizontalLayout());
			new Label(panelHorizontal).setText("Ingrese una fecha:");
			new TextBox(panelHorizontal).setWidth(100).bindValueToProperty("fecha");
			new Label(panelHorizontal).setText("Ingrese otra fecha:");
			new TextBox(panelHorizontal).setWidth(100).bindValueToProperty("otraFecha");
			new Button(mainPanel).onClick(()-> new Label(mainPanel).setText("FUNCIONA"));
	  }
	  
	  
	  public static void main(String[] args) {
		    new QueMePongoView().startApplication();
	  }

	  
}