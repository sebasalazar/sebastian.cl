package cl.sebastian.mantenedor.jsf;

import cl.sebastian.webutils.utils.FacesUtils;
import cl.sebastian.webutils.utils.SecurityUtils;
import java.io.Serializable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
@Component
@Scope("request")
@Qualifier("rutaBean")
public class RutaBean implements Serializable {

    private static Logger logger = Logger.getLogger(RutaBean.class);

    public String getRuta() {
        String ruta = "";
        try {
            ruta = FacesUtils.getRequest().getContextPath();
        } catch (Exception e) {
            logger.error(e);
            logger.debug("Error al obtener el contexto", e);
        }
        return ruta;
    }

    public boolean isAdmin() {
        return SecurityUtils.isUserInRole("ROLE_ADMIN");
    }
}