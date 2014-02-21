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
@Table(name = "posts")
public class Post extends PersistentEntityBase {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha",  nullable = false)
    private Date fecha = new Date();
    
    @Column(name = "autor", length = 255,  nullable = false)
    private String autor = null;
    
    @Column(name="titulo", length = 255,  nullable = false)
    private String titulo = null;
    
    @Column(name="texto", columnDefinition = "text")
    private String texto = null;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
