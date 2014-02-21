package cl.sebastian.modelo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
@Entity
@Table(name = "comentarios")
public class Comentario extends PersistentEntityBase {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha", nullable = false)
    private Date fecha = new Date();

    @Column(name = "autor", length = 255, nullable = false)
    private String autor = null;

    @Column(name = "email", length = 255)
    private String email = null;

    @Column(name = "web", length = 255)
    private String web = null;

    @Column(name = "comentario", columnDefinition = "text", nullable = false)
    private String comentario = null;

    @ManyToOne
    @JoinColumn(name = "post_fk", referencedColumnName = "pk", nullable = false)
    private Post post = null;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
