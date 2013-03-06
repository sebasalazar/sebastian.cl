package cl.sebastian.modelo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
@Entity
@Table(name="tips")
public class Tip extends AutoIncrementEntityBase {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha",  nullable = false)
    private Date fecha = new Date();

    @Column(name="titulo", length = 255, nullable = false)
    private String titulo = null;
    
    
    @Column(name="tip", columnDefinition = "text",  nullable = false)
    private String tip = null;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
