package server;

import java.util.Timer;
import java.util.TimerTask;

import model.LeerEmpresasProgramado;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Server {
	public static void main(String[] args) {
		new Bootstrap().init();
		Spark.port(9000);
		TimerTask cargarEmpresasBatch = new LeerEmpresasProgramado("archivoEmpresas.txt");
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(cargarEmpresasBatch, 0, 10*60*1000);	
		DebugScreen.enableDebugScreen();
		Router.configure();
	}

}
