package cl.sebastian.mantenedor.spring;

import cl.sebastian.mantenedor.servicio.ServicioAutenticacion;
import cl.sebastian.webutils.utils.FacesUtils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
@Service("proveedorAutenticacion")
public class ProveedorAutenticacion implements AuthenticationProvider {

    @Resource(name = "servicioAutenticacion")
    private ServicioAutenticacion servicioAutenticacion;
    private static final Logger logger = LoggerFactory.getLogger(ProveedorAutenticacion.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication user = null;

        logger.debug(authentication.toString());

        String userName = String.valueOf(authentication.getPrincipal()).toLowerCase();
        String password = String.valueOf(authentication.getCredentials());

        logger.info("Autenticando!!");
        logger.debug(userName);
        logger.debug(password);

        boolean authenticatedUser = servicioAutenticacion.isAutenticado(userName, password);
        if (!authenticatedUser) {
            String mensajeError = FacesUtils.getMessage("badPassword");
            throw new BadCredentialsException(mensajeError);
        } else {
            //userName = maintainerAuthenticationService.authenticationMainteiner(userName, password);
            if (!StringUtils.isEmpty(userName)) {
                user = createDummyUser(userName, password);
            } else {
                String mensajeError = FacesUtils.getMessage("badPassword");
                throw new BadCredentialsException(mensajeError);
            }
        }

        return user;
    }

    @Override
    public boolean supports(Class<?> type) {
        return UsernamePasswordAuthenticationToken.class.equals(type);
    }

    private Authentication createDummyUser(String userName, String password) {
        String rolStr = String.format("ROLE_%s", "ADMIN");
        logger.debug("Rol: " + rolStr);
        GrantedAuthority rol = new SimpleGrantedAuthority(rolStr);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(rol);

        Authentication user = new UsernamePasswordAuthenticationToken(userName, password, authorities);
        return user;
    }
}
