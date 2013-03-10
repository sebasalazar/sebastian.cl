package cl.sebastian.servicio.impl;

import cl.sebastian.modelo.Usuario;
import cl.sebastian.repositorio.UsuarioRepository;
import cl.sebastian.servicio.ServicioAutenticacion;
import java.io.Serializable;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
@Service("servicioAutenticacion")
public class ServicioAutenticacionImpl implements ServicioAutenticacion, Serializable {

    @Resource(name = "usuarioRepository")
    private UsuarioRepository usuarioRepository = null;
    private static Logger logger = LoggerFactory.getLogger(ServicioAutenticacionImpl.class);

    public boolean isAutenticado(String usuario, String contrasena) {
        boolean resultado = false;
        try {
            Usuario findByUsuario = usuarioRepository.findByUsuario(usuario);
            if (StringUtils.equals(contrasena, findByUsuario.getContrasena())) {
                resultado = true;
            }
        } catch (Exception e) {
            resultado = false;
            logger.error(e.toString());
            logger.debug("Error al obtener autenticación", e);
        }
        return resultado;
    }

    public Usuario autenticacion(String usuario, String contrasena) {
        Usuario autenticado = null;
        try {
            Usuario findByUsuario = usuarioRepository.findByUsuario(usuario);
            if (StringUtils.equals(contrasena, findByUsuario.getContrasena())) {
                autenticado = findByUsuario;
            }
        } catch (Exception e) {
            autenticado = null;
            logger.error(e.toString());
            logger.debug("Error al autenticar", e);
        }
        return autenticado;
    }
}
