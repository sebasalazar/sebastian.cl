package cl.sebastian.portal.servicio;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
public interface ServicioEnvioCorreo {

    public boolean enviarCorreoContacto(String nombre, String email, String asunto, String mensaje);
}
