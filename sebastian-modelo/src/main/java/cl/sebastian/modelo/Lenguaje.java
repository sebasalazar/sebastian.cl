package cl.sebastian.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
@Entity
@Table(name="lenguajes")
public class Lenguaje extends PersistentEntityBase {
    @Column(name="lenguaje", unique = true, length = 255,  nullable = false)
    private String lenguaje = null;
    
    @Column(name="brush", length = 255,  nullable = false)
    private String brush = null;

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public String getBrush() {
        return brush;
    }

    public void setBrush(String brush) {
        this.brush = brush;
    }
}
