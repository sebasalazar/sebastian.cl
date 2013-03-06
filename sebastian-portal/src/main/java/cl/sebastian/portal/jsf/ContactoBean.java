package cl.sebastian.portal.jsf;

import cl.sebastian.portal.servicio.ServicioEnvioCorreo;
import cl.sebastian.webutils.utils.FacesUtils;
import java.io.Serializable;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
@Component
@Scope("request")
@Qualifier("contactoBean")
public class ContactoBean implements Serializable {

    @Resource(name = "servicioEnvioCorreo")
    private ServicioEnvioCorreo servicioEnvioCorreo = null;
    private String nombre = null;
    private String email = null;
    private String asunto = null;
    private String mensaje = null;
    private boolean procesado = false;
    private boolean mailOk = false;
    private String titulo = null;
    private String texto = null;
    private static Logger logger = LoggerFactory.getLogger(ContactoBean.class);

    public void enviarMail() {
        try {
            procesado = true;
            boolean sendMail = servicioEnvioCorreo.enviarCorreoContacto(nombre, email, asunto, mensaje);
            if (sendMail) {
                mailOk = true;
                texto = FacesUtils.getFormattedMessage("correoEnviado");
            } else {
                texto = FacesUtils.getFormattedMessage("correoNoEnviado");
            }
        } catch (Exception e) {
            logger.error(e.toString());
            logger.debug("Error al enviar email", e);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isProcesado() {
        return procesado;
    }

    public void setProcesado(boolean procesado) {
        this.procesado = procesado;
    }

    public boolean isMailOk() {
        return mailOk;
    }

    public void setMailOk(boolean mailOk) {
        this.mailOk = mailOk;
    }
}
