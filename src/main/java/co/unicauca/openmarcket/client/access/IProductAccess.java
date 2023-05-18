package co.unicauca.openmarcket.client.access;

import co.unicauca.openmarcket.commons.domain.Product;

/**
 * Interface que define los servicios de persistencia de Clientes de la agencia
 *
 * @author Libardo Pantoja, Julio Hurtado
 */
public interface IProductAccess {

    /**
     * Buscar un cliente utilizando un socket
     *
     * @param id cedula del cliente
     * @return objeto cliente
     * @throws Exception error al buscar un cliente
     */
    public Product findProduct(String id) throws Exception;

    /**
     * Crea un Product
     *
     * @param product cliente de la agencia de viajes
     * @return devuelve la cedula del cliente creado
     * @throws Exception error crear el cliente
     */

    public String createProduct(Product product) throws Exception;
    public String deleteProduct(Product product) throws Exception;
}
