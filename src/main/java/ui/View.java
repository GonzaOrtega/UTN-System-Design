package ui;
import domain.*;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.MainWindow;

public class View extends MainWindow<ViewModel> {

	  public View(){
	    super(new ViewModel(new QueMePongoModel()));
	  }

	  @Override
	  public void createContents(Panel mainPanel){
	  }

	  public static void main(String[] args){
	    new View().startApplication();
	  }
	  
}