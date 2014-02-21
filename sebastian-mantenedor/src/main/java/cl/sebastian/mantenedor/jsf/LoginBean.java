package cl.sebastian.mantenedor.jsf;

import cl.sebastian.mantenedor.servicio.ServicioAutenticacion;
import cl.sebastian.webutils.utils.FacesUtils;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.Resource;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
@Qualifier("loginBean")
public class LoginBean implements Serializable {

    @Resource(name = "servicioAutenticacion")
    private ServicioAutenticacion servicioAutenticacion;
    private String password = null;
    private String user = null;
    private static final Logger logger = LoggerFactory.getLogger(LoginBean.class);

    public String doLogin() throws IOException, ServletException {
        String resultado = null;

        try {
            boolean ok = servicioAutenticacion.isAutenticado(user, password);
            if (ok) {
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

                RequestDispatcher dispatcher = null;
                dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_check");
                dispatcher.forward((ServletRequest) context.getRequest(),
                        (ServletResponse) context.getResponse());

                FacesContext.getCurrentInstance().responseComplete();
            } else {
                FacesUtils.errorMessage("badPassword");
            }
        } catch (Exception e) {
            logger.error("Problema al hacer login: {}", e.toString());
            logger.debug("Problema al hacer login", e);
        }
        return resultado;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
