package cl.sebastian.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
@Entity
@Table(name = "usuarios")
public class Usuario extends PersistentEntityBase {

    @Column(name = "usuario", unique = true, nullable = false, length = 255)
    private String usuario = null;
    
    @Column(name = "contrasena", nullable = false, length = 255)
    private String contrasena = null;
    
    @Column(name = "nombres", nullable = false, length = 255)
    private String nombres = null;
    
    @Column(name = "apellidos", nullable = false, length = 255)
    private String apellidos = null;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
