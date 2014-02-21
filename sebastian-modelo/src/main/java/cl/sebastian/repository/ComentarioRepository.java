package cl.sebastian.repository;

import cl.sebastian.modelo.Comentario;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sebastián Salazar Molina <ssalazar@experti.cl>
 */
@Resource(name = "comentarioRepository")
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
