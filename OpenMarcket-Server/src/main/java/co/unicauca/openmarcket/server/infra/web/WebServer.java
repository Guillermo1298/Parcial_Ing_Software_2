package co.unicauca.openmarcket.server.infra.web;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import co.unicauca.openmarcket.server.access.ProductRepositoryImplArrays;
import co.unicauca.openmarcket.server.domain.services.ProductService;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import java.util.concurrent.Executors;


public class WebServer {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8003), 0);
        OpenMarcketWebHandler webhandler = new OpenMarcketWebHandler();
        webhandler.setService(new ProductService(new ProductRepositoryImplArrays()));
        server.createContext("/client", webhandler);
        server.setExecutor(Executors.newCachedThreadPool()); // creates a default executor
        server.start();
        System.out.println("Servidor inicializado en el puerto 8003");
    }
}