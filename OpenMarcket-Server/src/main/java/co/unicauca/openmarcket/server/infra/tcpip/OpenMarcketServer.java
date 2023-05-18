/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarcket.server.infra.tcpip;

import co.unicauca.strategyserver.infra.ServerSocketMultiThread;
import co.unicauca.openmarcket.server.access.ProductRepositoryImplArrays;
import co.unicauca.openmarcket.server.domain.services.ProductService;
import java.util.Scanner;


/**
 *
 * @author ahurtado
 */
public class OpenMarcketServer {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese el puerto de escucha");
        int port = teclado.nextInt();
        ServerSocketMultiThread myServer = new ServerSocketMultiThread(port);
        OpenMarcketHandler myHandler = new OpenMarcketHandler();
        myHandler.setService(new ProductService(new ProductRepositoryImplArrays()));
        myServer.setServerHandler(myHandler);
        myServer.startServer();
    }
    
}
