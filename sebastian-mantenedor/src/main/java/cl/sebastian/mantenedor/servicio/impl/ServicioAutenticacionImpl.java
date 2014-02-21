package cl.sebastian.mantenedor.servicio.impl;

import cl.sebastian.mantenedor.servicio.ServicioAutenticacion;
import cl.sebastian.servicio.ServicioUsuario;
import java.io.Serializable;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sebastián Salazar Molina <ssalazar@experti.cl>
 */
@Service("servicioAutenticacion")
public class ServicioAutenticacionImpl implements ServicioAutenticacion, Serializable {

    @Resource(name = "servicioUsuario")
    private ServicioUsuario servicioUsuario;

    private static final Logger logger = LoggerFactory.getLogger(ServicioAutenticacionImpl.class);

    public boolean isAutenticado(String usuario, String password) {
        return servicioUsuario.isAutenticado(usuario, password);
    }

}
