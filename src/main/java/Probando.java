
//YA SE QUE ESTO ES HORRIBLE PERO GUENO HABIA QUE APURAR EL TRAMITE DESPUES
//AVERIGUO COMO ES PARA ARMAR LOS TEST
import java.util.stream.*;
import java.util.List;
import java.util.HashSet;

public class Probando {
	  public static void main(String[] args)
	  {
	    Usuario juan = new Usuario(198,"JFQ8");
	    System.out.println(juan.guardarropas);
	    System.out.println("-----//----");
	    Guardarropa armanio = new Guardarropa();
	    armanio.agregarRopasLocas();
	    System.out.println((armanio.prendas.stream().map(x->x.tipo.prenda.categoria)).collect(Collectors.toList()));
	    System.out.println();
	    System.out.println((armanio.devolverAtuendos().stream().map(x->x.stream().map(y->y.tipo.prenda.categoria).collect(Collectors.toList()))).collect(Collectors.toList()));

	  }
	}

