package cl.sebastian.modelo;

import org.apache.commons.lang.ObjectUtils;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
public abstract class EntityBase extends BaseBean {

    private static final long serialVersionUID = 2202015144287538980L;

    /**
     * Obtiene el id unico del objeto
     *
     * @return id del objeto
     */
    public abstract Long getId();

    /**
     * Permite setear el id unico del objeto
     *
     * @param id id del objeto
     */
    public abstract void setId(Long id);

    @Override
    public int hashCode() {
        int result = 0;
        if (this.getId() != null) {
            result = this.getId().hashCode();
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o != null && getClass().isAssignableFrom(o.getClass())) {
            EntityBase entity = (EntityBase) o;
            result = ObjectUtils.equals(this.getId(), entity.getId());
        }
        return result;
    }
}
