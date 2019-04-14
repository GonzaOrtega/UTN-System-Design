public class ProbandoAgain{
	  public static void main(String[] args){

	    Guardarropa armario = new Guardarropa();
	    armario.agregarPrenda(new Prenda(Color.ROSA,Color.VERDE, new Tipo(Categoria.SUPERIOR), Material.ALGODON));
	    armario.agregarPrenda(new Prenda(Color.ROSA,Color.AZUL, new Tipo(Categoria.INFERIOR), Material.ALGODON));
	    Guardarropa otroArmario = new Guardarropa();
	    System.out.println("Armario.prendas=");
	    System.out.println(armario.prendas);
	    otroArmario.agregarPrenda(new Prenda(Color.AZUL,Color.VERDE, new Tipo(Categoria.CALZADO), Material.ALGODON));
	    otroArmario.agregarPrenda(new Prenda(Color.AZUL,Color.VERDE, new Tipo(Categoria.ACCESORIO), Material.ALGODON));
	    System.out.println("OtroArmario.prendas=");
	    System.out.println(otroArmario.prendas);
	    Usuario juan = new Usuario(198,"JFQ8");
	    juan.agregarGuardarropa(armario);
	    juan.agregarGuardarropa(otroArmario);
	  //  System.out.println(juan.pedirAtuendo());
	    
	  }
}

 