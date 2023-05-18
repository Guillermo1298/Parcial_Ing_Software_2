
package co.unicauca.openmarcket.client.domain.services;

import co.unicauca.openmarcket.client.domain.User;

/**
 * Servicio de usuarios del sistema
 * @author Libardo, Julio
 */
public class UserService {
    /**
     * Autentica el usuario en el sistema
     * Por ahora no hace ninguna validación
     * @param login login de usuario
     * @param password contraseña
     * @return objeto User cuando la autenticación es exitosa
     */
    public User autenticacion(String login, String password) {
        return null;
    }
}