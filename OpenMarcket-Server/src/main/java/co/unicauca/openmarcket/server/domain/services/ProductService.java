package co.unicauca.openmarcket.server.domain.services;

import co.unicauca.openmarcket.commons.domain.Product;
import co.unicauca.openmarcket.commons.infra.JsonError;
import co.unicauca.openmarcket.commons.infra.Utilities;
import co.unicauca.openmarcket.server.access.IProductRepository;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio de clientes. Da acceso a la lógica de negocio
 *
 * @author Libardo, Julio
 */
public class ProductService {

    /**
     * Repositorio de clientes
     */
    IProductRepository repo;

    /**
     * Constructor parametrizado. Hace inyeccion de dependencias
     *
     * @param repo repositorio de tipo IProductRepository
     */
    public ProductService(IProductRepository repo) {
        this.repo = repo;
    }

    /**
     * Buscar un cliente
     *
     * @param id cedula
     * @return objeto tipo Product
     */
    public synchronized Product findProduct(String id) {
        return repo.findProduct(id);
    }

    /**
     * Crea un nuevo product. Aplica validaciones de negocio
     *
     * @param product cliente
     * @return devuelve la cedula del product creado
     */
    public synchronized String createProduct(Product product) {
        List<JsonError> errors = new ArrayList<>();
  
        // Validaciones y reglas de negocio
        if (product.getProductId().isEmpty() || product.getName().isEmpty()
                || product.getDescription().isEmpty()) {
           errors.add(new JsonError("400", "BAD_REQUEST","id, nombres, apellidos, email y género son obligatorios. "));
        }
        
        // Que no esté repetido
        
        Product productSearched = this.findProduct(product.getProductId());
        if (productSearched != null){
            errors.add(new JsonError("400", "BAD_REQUEST","El Producto ya existe. "));
        }
        
       if (!errors.isEmpty()) {
            Gson gson = new Gson();
            String errorsJson = gson.toJson(errors);
            return errorsJson;
        }             
        return repo.createProduct(product);
    }


}
