package cl.sebastian.servicio;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
public interface ServicioEmail {
    public boolean sendMail(final String to, final String from, final String subject, final String message);
    public boolean sendMail(final String to, final String from, final String subject, final String message, final String path);
    public boolean sendMail(final String to, final String from, final String subject, final String message, final byte[] archivo, final String nombreArchivo, final String tipoMime);
}
