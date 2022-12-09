package cl.chile.ionix.test.tecnico.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

/**
 * Instantiates a new users model token.
 */
@Data
@Entity
@Table(name = "user_token")
public class UsersModelToken {
	
    /** The id usuario. */
    @Id
    private Integer idUsuario;

    /** The user name. */
    @Column(name = "nombre")
    private String userName;

    /** The password. */
    @Column(name = "clave")
    private String password;

    /** The estado. */
    @Column(name = "estado")
    private boolean estado;
    
    /** The rols. */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_rol",
            joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "idRol"))
    private List<RolModel> rols;

}
