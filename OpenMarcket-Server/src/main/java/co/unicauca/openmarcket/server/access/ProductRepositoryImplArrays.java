package co.unicauca.openmarcket.server.access;

import co.unicauca.openmarcket.commons.domain.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de IProductRepository. Utilliza arreglos en memoria
 *
 * @author Libardo Pantoja, Julio Hurtado
 */
public final class ProductRepositoryImplArrays implements IProductRepository {

    /**
     * Array List de clientes
     */
    public static List<Product> product;

    public ProductRepositoryImplArrays() {
        if (product == null){
            product = new ArrayList();
        }
        
        if (product.size() == 0){
            inicializar();
        }
    }

    public void inicializar() {
        product.add(new Product("98000001", "Pasta", "Sanchez"));
        product.add(new Product("98000002", "Harina", "Pantoja"));
        product.add(new Product("98000003", "Arroz", "Pantoja"));
        product.add(new Product("98000004", "Cebolla", "Arevalo"));
        product.add(new Product("98000005", "Huevos", "Perez"));
        product.add(new Product("98000006", "Leche", "Mosquera"));
        product.add(new Product("98000007", "Cereal", "Gutierres Sanchez"));
        product.add(new Product("98000008", "Azucar", "Bravo Bravo"));
        product.add(new Product("98000009", "Salsa", "Mendez Bravo"));
        product.add(new Product("98000010", "Pan", "Ponce Yepes"));

    }

    /**
     * Busca u Product en el arreglo
     *
     * @param id cedula del customer
     * @return objeto Product
     */
    @Override
    public Product findProduct(String id) {
        for (Product customer : product) {
            if (customer.getProductId().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public String createProduct(Product producto) {
        product.add(producto);
        return producto.getProductId();
    }

    @Override
    public boolean deleteProduct(String id) {
        Product producto = findProduct(id);
        if(producto!=null){
            product.remove(producto);
            return true;
        }
        return false;
    }

}
