package cl.sebastian.mantenedor.servicio;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
public interface ServicioAutenticacion {

    public boolean isAutenticado (String usuario, String password);
}
