package cl.sebastian.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Sebastián Salazar Molina <ssalazar@experti.cl>
 */
@Entity
@Table(name = "categorias")
public class Categoria extends PersistentEntityBase {

    @Column(name = "nombre", unique = true)
    private String nombre = null;
    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion = null;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
