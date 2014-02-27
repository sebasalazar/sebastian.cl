package cl.sebastian.repository;

import cl.sebastian.modelo.Categoria;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.cl>
 */
@Resource(name = "categoriaRepository")
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
