package cl.sebastian.webutils.utils;

import java.io.Serializable;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
public abstract class SecurityUtils implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtils.class);

    public static String getRemoteUser() {
        String remoteUser = "";
        try {
            remoteUser = SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (Exception e) {
            remoteUser = "";
            logger.error("Error al obtener usuario: {}", e.toString());
        }
        return remoteUser;
    }

    public static boolean isUserInRole(String role) {
        boolean resultado = false;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            resultado = authentication.getAuthorities().contains(new SimpleGrantedAuthority(role));
        } catch (Exception e) {
            resultado = false;
            logger.error("Error al obtener rol de usuario: {}", e.toString());
        }
        return resultado;
    }

    public static String hashSha256(String text) {
        String hash = "";
        try {
            hash = DigestUtils.sha256Hex(text);
        } catch (Exception e) {
            hash = "";
            logger.error("Error al hashear SHA256: {}", e.toString());
        }
        return hash;
    }
}
