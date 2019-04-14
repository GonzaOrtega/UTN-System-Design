//YA SE QUE ESTO ES HORRIBLE PERO GUENO HABIA QUE APURAR EL TRAMITE DESPUES
//AVERIGUO COMO ES PARA ARMAR LOS TEST
public class Probando {
	  public static void main(String[] args)
	  {
	    Usuario juan = new Usuario(198,"JFQ8");
	    juan.crearGuardarropa();
	    System.out.println(juan.guardarropas);
	    System.out.println("---------");
	    Guardarropa armanio = new Guardarropa();
	    armanio.agregarRopasLocas();
	    System.out.println(armanio.prendas);
	  }
	}

