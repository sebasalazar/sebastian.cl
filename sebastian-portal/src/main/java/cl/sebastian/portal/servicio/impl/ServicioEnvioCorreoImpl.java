package cl.sebastian.portal.servicio.impl;

import cl.sebastian.portal.servicio.ServicioEnvioCorreo;
import cl.sebastian.servicio.ServicioEmail;
import cl.sebastian.webutils.utils.LocaleUtils;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
@Service("servicioEnvioCorreo")
public class ServicioEnvioCorreoImpl implements ServicioEnvioCorreo, Serializable {

    @Resource(name = "correoContacto")
    private String correoContacto;
    @Resource(name = "mailCartero")
    private String mailCartero;
    @Resource(name = "velocityEngine")
    private VelocityEngine velocityEngine;
    @Resource(name = "servicioEmail")
    private ServicioEmail servicioEmail;
    private static final Logger logger = LoggerFactory.getLogger(ServicioEnvioCorreoImpl.class);

    public boolean enviarCorreoContacto(String nombre, String email, String asunto, String mensaje) {
        boolean resultado = false;
        try {
            Map<String, Object> datos = new HashMap<String, Object>();
            datos.put("titulo", "Correo de Contacto portal sebastian.cl");
            datos.put("asunto", asunto);
            datos.put("nombre", nombre);
            datos.put("email", email);
            datos.put("mensaje", mensaje);
            String lang = LocaleUtils.getLang();

            String subject = "[sebastian.cl] " + asunto;
            String message = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, lang + "/contacto.vm", "utf8", datos);
            logger.debug(message);
            resultado = servicioEmail.sendMail(correoContacto, mailCartero, subject, message);
        } catch (Exception e) {
            logger.error("Error al enviar Correo de Contacto: {}", e.toString());
        }
        return resultado;
    }
}
