package domain;
import java.util.*;

public class QueMePongoModel {
	public Set<Evento> eventos(){
		return RepositorioDeUsuarios.getInstance().eventos();
	}
}
