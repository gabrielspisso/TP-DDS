package server;


import spark.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;
import static spark.Spark.*;
import static spark.debug.DebugScreen.*;

public class FileUploader {

    public static void main(String[] args) {
    	Spark.port(9000);
        enableDebugScreen();

        get("/", (req, res) ->
                  "<form method='post' enctype='multipart/form-data'>" // note the enctype
                + "    <input type='file' name='uploaded_file' accept='.txt'>" // make sure to call getPart using the same "name" in the post
                + "    <button>Upload picture</button>"
                + "</form>"
        );

        post("/", (req, res) -> {

            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

            Part parte = null;
            InputStream input = null;
            String contenido = "";
            try { // getPart needs to use same "name" as input field in form
            	parte = req.raw().getPart("uploaded_file");
            	input = parte.getInputStream();
            	contenido = convertStreamToString(input);
            }
            catch(Exception ex) {
            	
            }

            //logInfo(req, tempFile);
            return "<h1>You uploaded this file: "+ getFileName(parte) + " y el contenido es este: " + contenido;

        });

    }

    static String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
    

    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}