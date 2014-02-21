package cl.sebastian.servicio.impl;

import cl.sebastian.modelo.Usuario;
import cl.sebastian.repository.UsuarioRepository;
import cl.sebastian.servicio.ServicioUsuario;
import java.io.Serializable;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
@Service("servicioUsuario")
public class ServicioUsuarioImpl implements ServicioUsuario, Serializable {

    @Resource(name = "usuarioRepository")
    private UsuarioRepository usuarioRepository;
    private static final Logger logger = LoggerFactory.getLogger(ServicioUsuarioImpl.class);

    public boolean isAutenticado(String usuario, String contrasena) {
        boolean resultado = false;
        try {
            Usuario findByUsuario = usuarioRepository.findByUsuario(usuario);
            if (StringUtils.equals(contrasena, findByUsuario.getContrasena())) {
                resultado = true;
            }
        } catch (Exception e) {
            resultado = false;
            logger.error("Error al obtener autenticación: {}", e.toString());
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
            logger.error("Error al autenticar: {}", e.toString());
            logger.debug("Error al autenticar", e);
        }
        return autenticado;
    }
}
