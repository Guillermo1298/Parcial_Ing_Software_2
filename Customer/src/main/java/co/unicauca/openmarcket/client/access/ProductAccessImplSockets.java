
package co.unicauca.openmarcket.client.access;

import co.unicauca.openmarcket.commons.infra.Protocol;
import co.unicauca.openmarcket.commons.domain.Product;
import co.unicauca.openmarcket.commons.infra.JsonError;
import co.unicauca.openmarcket.client.infra.OpenMarcketSocket;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servicio de Cliente. Permite hacer el CRUD de clientes solicitando los
 * servicios con la aplicación server. Maneja los errores devueltos
 *
 * @author Libardo Pantoja, Julio A. Hurtado
 */
public class ProductAccessImplSockets implements IProductAccess {
    /**
     * El servicio utiliza un socket para comunicarse con la aplicación server
     */
    private OpenMarcketSocket mySocket;

    public ProductAccessImplSockets() {
        mySocket = new OpenMarcketSocket();
    }

    /**
     * Busca un Product. Utiliza socket para pedir el servicio al servidor
     *
     * @param id cedula del cliente
     * @return Objeto Product
     * @throws Exception cuando no pueda conectarse con el servidor
     */
    @Override
    public Product findProduct(String id) throws Exception {
        String jsonResponse = null;
        String requestJson = doFindProductRequestJson(id);
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Encontró el product
                Product product = jsonToProduct(jsonResponse);
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ("+jsonResponse.toString()+ ")");
                return product;
            }
        }

    }

    /**
     * Crea un Product. Utiliza socket para pedir el servicio al servidor
     *
     * @param product cliente de la agencia de viajes
     * @return devuelve la cedula del cliente creado
     * @throws Exception error crear el cliente
     */
    @Override
    public String createProduct(Product product) throws Exception {
        String jsonResponse = null;
        String requestJson = doCreateProductRequestJson(product);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {

            if (jsonResponse.contains("error")) {
                //Devolvió algún error                
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Agregó correctamente, devuelve la cedula del product 
                return product.getProductId();
            }

        }

    }
    //****************************************************************************************************************************************************************************
    @Override
    public String deleteProduct(Product product) throws Exception {
        String jsonResponse = null;
        String requestJson = doDeleteProductRequestJson(product);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {

            if (jsonResponse.contains("error")) {
                //Devolvió algún error                
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Agregó correctamente, devuelve la cedula del product 
                return product.getProductId();
            }

        }

    }
    /***********************************************************************************************************************************************************************
    /**
     * Extra los mensajes de la lista de errores
     * @param jsonResponse lista de mensajes json
     * @return Mensajes de error
     */
    private String extractMessages(String jsonResponse) {
        JsonError[] errors = jsonToErrors(jsonResponse);
        String msjs = "";
        for (JsonError error : errors) {
            msjs += error.getMessage();
        }
        return msjs;
    }

    /**
     * Convierte el jsonError a un array de objetos jsonError
     *
     * @param jsonError
     * @return objeto MyError
     */
    private JsonError[] jsonToErrors(String jsonError) {
        Gson gson = new Gson();
        JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }

    /**
     * Crea una solicitud json para ser enviada por el socket
     *
     *
     * @param idProduct identificación del producto
     * @return solicitud de consulta del cliente en formato Json, algo como:
     * {"resource":"product","action":"get","parameters":[{"name":"id","value":"98000001"}]}
     */
    private String doFindProductRequestJson(String idProduct) {

        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("get");
        protocol.addParameter("id", idProduct);

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    /**
     * Crea la solicitud json de creación del product para ser enviado por el
     * socket
     *
     * @param product objeto product
     * @return devulve algo como:
     * {"resource":"product","action":"post","parameters":[{"name":"id","value":"980000012"},{"name":"fistName","value":"Juan"},...}]}
     */
    private String doCreateProductRequestJson(Product product) {

        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("post");
        protocol.addParameter("Productid", product.getProductId());
        protocol.addParameter("Name", product.getName());
        protocol.addParameter("Description", product.getDescription());

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    private String doDeleteProductRequestJson(Product product) {

        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("post");
        protocol.removeParameter(product);

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    /**
     * Convierte jsonProduct, proveniente del server socket, de json a un
     * objeto Product
     *
     * @param jsonProduct objeto cliente en formato json
     */
    private Product jsonToProduct(String jsonProduct) {

        Gson gson = new Gson();
        Product product = gson.fromJson(jsonProduct, Product.class);
        return product;

    }

}
