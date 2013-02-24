package cl.sebastian.portal.jsf;

import cl.sebastian.webutils.utils.FacesUtils;
import cl.sebastian.webutils.utils.FechaUtils;
import cl.sebastian.webutils.utils.LocaleUtils;
import cl.sebastian.webutils.utils.SecurityUtils;
import java.io.Serializable;
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
@Qualifier("sebaBean")
public class SebaBean implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(SebaBean.class);

    public String getRuta() {
        String ruta = "";
        try {
            ruta = FacesUtils.getRequest().getContextPath();
        } catch (Exception e) {
            logger.error(e.toString());
            logger.debug("Error al obtener el contexto", e);
        }
        return ruta;
    }

    public String getIdioma() {
        String idioma = "";
        try {
            idioma = LocaleUtils.getLang();
        } catch (Exception e) {
            logger.error(e.toString());
            logger.debug("Error al obtener el idioma", e);
        }
        return idioma;
    }

    public boolean isAdmin() {
        boolean admin = false;
        try {
            admin = SecurityUtils.isUserInRole("ROLE_ADMIN");
        } catch (Exception e) {
            logger.error(e.toString());
            logger.debug("Error al obtener rol", e);
        }
        return admin;
    }

    public String getYear() {
        String year = null;
        try {
            year = FechaUtils.getYear();
        } catch (Exception e) {
            logger.error(e.toString());
            logger.debug("Error al obtener año", e);
        }
        return year;
    }
}