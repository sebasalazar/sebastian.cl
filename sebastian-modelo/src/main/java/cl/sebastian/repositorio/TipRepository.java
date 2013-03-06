package cl.sebastian.repositorio;

import cl.sebastian.modelo.Tip;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
@Resource(name="tipRepository")
public interface TipRepository extends JpaRepository<Tip, Long> {
    public List<Tip> findAllOrderByFechaDesc();
}
