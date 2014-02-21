package cl.sebastian.repository;

import cl.sebastian.modelo.Post;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sebastián Salazar Molina <ssalazar@experti.cl>
 */
@Resource(name = "postRepository")
public interface PostRepository extends JpaRepository<Post, Long> {

}
