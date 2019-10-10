package server;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) {
		Spark.port(9001); //levanta en el puerto 9000
		DebugScreen.enableDebugScreen();
		Spark.staticFileLocation("/public");
		Router.instance().configurar();
		//Spark.init();
	}

}
