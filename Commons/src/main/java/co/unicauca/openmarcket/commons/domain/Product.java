package co.unicauca.openmarcket.commons.domain;

/**
 *
 * @author Libardo, Julio
 */
public class Product {


    private String productId;

    private String name;
    
    private String description;
    

    public Product(String productId, String name, String description) {
        this.productId = productId;
        this.name = name;
        this.description = description;
    }

    public Product() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }   
}
