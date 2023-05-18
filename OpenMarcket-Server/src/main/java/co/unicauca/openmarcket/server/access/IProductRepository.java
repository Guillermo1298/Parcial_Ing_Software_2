package co.unicauca.openmarcket.server.access;

import co.unicauca.openmarcket.commons.domain.Product;

/**
 * Interface del respositorio de clientes
 * @author Libardo Pantoja
 */
public interface IProductRepository {
    /**
     * Busca un Product por su ceduka
     * @param id cedula del cliente
     * @return  objeto de tipo Product
     */
    
    public Product findProduct(String id);
    public String createProduct(Product product);
    public boolean deleteProduct(String id);

}