package ui;
import domain.*;
import org.uqbar.commons.model.annotations.Observable;

@Observable
public class QueMePongoViewModel{
	private QueMePongoModel model;

  	public QueMePongoViewModel(QueMePongoModel unModel) {
  		this.model = unModel;
  	}

	public QueMePongoModel getModel() {
		return model;
	}

	public void setModel(QueMePongoModel model) {
		this.model = model;
	}
  	
  	

}