package cl.sebastian.modelo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
@MappedSuperclass
public abstract class PersistentEntityBase extends EntityBase {

    private static final long serialVersionUID = 2202015144287538980L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    /**
     * Obtiene el id unico del objeto
     *
     * @return id del objeto
     */
    public Long getId() {
        return id;
    }

    /**
     * Permite setear el id unico del objeto
     *
     * @param id id del objeto
     */
    public void setId(Long id) {
        this.id = id;
    }
}
