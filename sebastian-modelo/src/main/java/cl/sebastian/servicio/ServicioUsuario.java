package cl.sebastian.servicio;

import cl.sebastian.modelo.Usuario;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
public interface ServicioUsuario {

    public boolean isAutenticado(String usuario, String contrasena);

    public Usuario autenticacion(String usuario, String contrasena);
}
