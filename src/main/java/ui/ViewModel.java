package ui;
import domain.*;
import org.uqbar.commons.model.annotations.Observable;

@Observable
public class ViewModel {

  private QueMePongoModel model;

  public ViewModel(QueMePongoModel unModel) {
    this.model = unModel;
  }

}