package cl.sebastian.repository;

import cl.sebastian.modelo.Usuario;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
@Resource(name = "usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByUsuario(String usuario);
}
