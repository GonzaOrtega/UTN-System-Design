package ui;
import domain.*;
import java.awt.Color;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.MainWindow;

public class QueMePongoView extends MainWindow<QueMePongoViewModel> {

	  public QueMePongoView(){
	    super(new QueMePongoViewModel(new QueMePongoModel()));
	  }

	  public void createContents(Panel mainPanel){
			new Label(mainPanel).setText("Que Me Pongo").setBackground(Color.ORANGE);
	  }
	  
	  public static void main(String[] args) {
		    new QueMePongoView().startApplication();
	  }

	  
}