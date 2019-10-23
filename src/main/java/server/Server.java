package server;


import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) {
		Spark.port(9000); //levanta en el puerto 9000
		Spark.staticFileLocation("/public");
		Spark.init();
		Router.instance().configurar();
		DebugScreen.enableDebugScreen();

		//Spark.init();

	}

}
