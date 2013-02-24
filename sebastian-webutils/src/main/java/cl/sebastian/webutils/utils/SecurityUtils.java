package cl.sebastian.webutils.utils;

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
public abstract class SecurityUtils {
    private static Logger logger = LoggerFactory.getLogger(SecurityUtils.class);
    
    public static String getRemoteUser() {
        String remoteUser = "";
        try {
            remoteUser = SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (Exception e) {
            logger.error(e.toString());
            logger.debug(e.toString());
        }
        return remoteUser;
    }
    
    public static boolean isUserInRole(String role) {
        boolean resultado = false;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            resultado = authentication.getAuthorities().contains(new SimpleGrantedAuthority(role));
        } catch (Exception e) {
            logger.error(e.toString());
            logger.debug(e.toString());
        }
        return resultado;
    }
    
    public static String hashSha256(String text) {
        String hash = "";
        try {
            hash = DigestUtils.sha256Hex(text);
        } catch (Exception e) {
            logger.error(e.toString());
            logger.debug(e.toString());
        }
        return hash;
    }
}
