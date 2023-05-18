package co.unicauca.openmarcket.client.access;

import co.unicauca.openmarcket.commons.infra.Utilities;

/**
 * Fabrica que se encarga de instanciar ProductServiceImplSockets o cualquier
 * otro que se cree en el futuro.
 *
 * @author Libardo, Julio
 */
public class Factory {

    private static Factory instance;

    private Factory() {
    }

    /**
     * Clase singleton
     *
     * @return
     */
    public static Factory getInstance() {

        if (instance == null) {
            instance = new Factory();
        }
        return instance;

    }

    /**
     * Método que crea una instancia concreta de la jerarquia IProductService
     *
     * @return una clase hija de la abstracción IRepositorioClientes
     */
    public IProductAccess getProductService() {

        IProductAccess result = null;
        String type = Utilities.loadProperty("customer.service");

        switch (type) {
            case "default":
                result = new ProductAccessImplSockets();
                break;
        }

        return result;

    }
}
