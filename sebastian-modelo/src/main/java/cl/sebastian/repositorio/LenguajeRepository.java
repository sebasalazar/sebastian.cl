package cl.sebastian.repositorio;

import cl.sebastian.modelo.Lenguaje;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
public interface LenguajeRepository extends JpaRepository<Lenguaje, Long> {
    public Lenguaje findByLenguaje(String lenguaje);
}
