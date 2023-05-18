/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarcket.server.infra.tcpip;

import co.unicauca.strategyserver.infra.ServerHandler;
import co.unicauca.openmarcket.commons.domain.Product;
import co.unicauca.openmarcket.commons.infra.JsonError;
import co.unicauca.openmarcket.commons.infra.Protocol;
import co.unicauca.openmarcket.server.access.ProductRepositoryImplArrays;
import co.unicauca.openmarcket.server.domain.services.ProductService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ahurtado
 */
public class OpenMarcketHandler extends ServerHandler {

     /**
     * Servicio de clientes
     */
    private static ProductService service;

    public OpenMarcketHandler() {
    }

     /**
     * Procesar la solicitud que proviene de la aplicación cliente
     *
     * @param requestJson petición que proviene del cliente socket en formato
     * json que viene de esta manera:
     * "{"resource":"customer","action":"get","parameters":[{"name":"id","value":"1"}]}"
     *
     */
   
    
    @Override
    public String processRequest(String requestJson) {
        // Convertir la solicitud a objeto Protocol para poderlo procesar
        Gson gson = new Gson();  
        Protocol protocolRequest;
        protocolRequest = gson.fromJson(requestJson, Protocol.class);
        String response="";
        switch (protocolRequest.getResource()) {
            case "product":
                if (protocolRequest.getAction().equals("get")) {
                    // Consultar un customer
                    response = processGetProduct(protocolRequest);
                }

                if (protocolRequest.getAction().equals("post")) {
                    // Agregar un customer    
                    response = processPostProduct(protocolRequest);

                }
                break;
        }
        return response;

    }

    /**
     * Procesa la solicitud de consultar un customer
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private String processGetProduct(Protocol protocolRequest) {
        // Extraer la cedula del primer parámetro
        String productId = protocolRequest.getParameters().get(0).getValue();
        Product product = service.findProduct(productId);
        if (product == null) {
            String errorJson = generateNotFoundErrorJson();
            return errorJson;
        } else {
            return objectToJSON(product);
        }
    }

    /**
     * Procesa la solicitud de agregar un customer
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private String processPostProduct(Protocol protocolRequest) {
        Product product = new Product();
        // Reconstruir el customer a partid de lo que viene en los parámetros
        product.setProductId(protocolRequest.getParameters().get(0).getValue());
        product.setName(protocolRequest.getParameters().get(1).getValue());
        product.setDescription(protocolRequest.getParameters().get(2).getValue());

        String response = getService().createProduct(product);
        return response;
    }

    /**
     * Genera un ErrorJson de cliente no encontrado
     *
     * @return error en formato json
     */
    private String generateNotFoundErrorJson() {
        List<JsonError> errors = new ArrayList<>();
        JsonError error = new JsonError();
        error.setCode("404");
        error.setError("NOT_FOUND");
        error.setMessage("Cliente no encontrado. Cédula no existe");
        errors.add(error);

        Gson gson = new Gson();
        String errorsJson = gson.toJson(errors);

        return errorsJson;
    }

    /**
     * @return the service
     */
    public ProductService getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(ProductService service) {
        this.service = service;
    } 
}
