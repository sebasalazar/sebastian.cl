package cl.sebastian.portal.jsf;

import cl.sebastian.webutils.utils.FacesUtils;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
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
@Qualifier("menuBean")
public class MenuBean implements Serializable {

    public final static int MENU_HOME = 0;
    public final static int MENU_CONTACTO = 1;
    public final static int MENU_ERROR = -1;
    private Integer activeMenuIndex = null;
    private static Logger logger = LoggerFactory.getLogger(MenuBean.class);

    @PostConstruct
    public void iniciar() {
        try {

            // Esto depende del orden que coloquemos los menus
            String viewId = FacesUtils.getFacesContext().getViewRoot().getViewId();
            if (StringUtils.contains(viewId, "index")) {
                activeMenuIndex = MENU_HOME;
            } else if (StringUtils.contains(viewId, "contacto")) {
                activeMenuIndex = MENU_CONTACTO;
            } else {
                activeMenuIndex = MENU_ERROR;
            }
        } catch (Exception e) {
            activeMenuIndex = -1;
            logger.error(e.toString());
            logger.debug("Error al iniciar PostConstruct", e);
        }
    }

    public Integer getActiveMenuIndex() {
        return activeMenuIndex;
    }

    public void setActiveMenuIndex(Integer activeMenuIndex) {
        this.activeMenuIndex = activeMenuIndex;
    }
}
