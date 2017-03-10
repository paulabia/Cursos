package br.com.alura.loja.servidor;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Created by apq on 03/03/17.
 */
public class Servidor {

    public static void main(String [] args ) throws IOException {
        HttpServer server = startServer();

        System.in.read();
        server.stop();

    }

    public static HttpServer startServer() {
        ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
        URI uri = URI.create("http://localhost:8080/");
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
        System.out.println("Servidor rodando");

        return server;
    }
}
