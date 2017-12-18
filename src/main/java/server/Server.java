package server;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import model.Empresa;
import model.LeerEmpresasProgramado;
import model.repositorios.RepositorioDeArchivos;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Server {
	public static void main(String[] args) {
		new Bootstrap().init();
		RepositorioDeArchivos.getInstance().defaultBucket();
		
		TimerTask cargarEmpresasBatch = new LeerEmpresasProgramado("archivoEmpresas.txt");
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(cargarEmpresasBatch, 0, 30*1000);
        ProcessBuilder process = new ProcessBuilder();
        Integer port = 9000;
        if(process.environment().get("PORT") != null) { 
        	port = Integer.parseInt(process.environment().get("PORT"));
        }
        Spark.port(port);
        DebugScreen.enableDebugScreen();
		Router.configure();
	}

}
