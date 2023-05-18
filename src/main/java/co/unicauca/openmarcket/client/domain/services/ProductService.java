package co.unicauca.openmarcket.client.domain.services;

import co.unicauca.openmarcket.commons.domain.Product;
import co.unicauca.openmarcket.client.access.IProductAccess;

/**
 * Es una fachada para comunicar la presentación con el
 * dominio
 *
 * @author Libardo Pantoja, Julio Hurtado
 */
public class ProductService {

    private final IProductAccess access;

    /**
     * Constructor privado que evita que otros objetos instancien
     * @param service implementacion de tipo IProductService
     */
    public ProductService(IProductAccess access) {
        this.access = access;
    }

    /**
     * Busca un cliente en el servidor remoto
     *
     * @param id identificador del cliente
     * @return Objeto tipo Cliente, null si no lo encuentra
     * @throws java.lang.Exception la excepcio se lanza cuando no logra conexión
     * con el servidor
     */
    public Product findProduct(String id) throws Exception {
        return access.findProduct(id);

    }
    
    public String createProduct(Product customer) throws Exception {
        return access.createProduct(customer);

    }   
    
    public String deleteProduct(Product customer) throws Exception {
        return access.deleteProduct(customer);

    } 

}
