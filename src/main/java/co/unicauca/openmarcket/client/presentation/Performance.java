/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarcket.client.presentation;

import co.unicauca.openmarcket.client.access.Factory;
import co.unicauca.openmarcket.client.access.IProductAccess;
import co.unicauca.openmarcket.client.domain.services.ProductService;
import static co.unicauca.openmarcket.client.infra.Messages.successMessage;
import co.unicauca.openmarcket.commons.domain.Product;
import java.util.Scanner;

/**
 *
 * @author ahurtado
 */
public class Performance {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
  
        IProductAccess service = Factory.getInstance().getProductService();
        // Inyecta la dependencia
        ProductService productService = new ProductService(service);
        try {
            Product product;
            for (int i=1; i<2 ; i++){
               product = productService.findProduct("9800000"+i);
               System.out.println(product.getName());
        }
            
           
        } catch (Exception ex) {
           
        }
        
    }
    
}
