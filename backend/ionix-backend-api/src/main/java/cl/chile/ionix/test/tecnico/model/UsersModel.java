package cl.chile.ionix.test.tecnico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * The Class UsersModel.
 */
@Entity

/**
 * Instantiates a new users model.
 */
@Data
@Table(name="Users")
public class UsersModel {
	
	/** The id. */
	@Id
	@Size(max=38)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_user", nullable = false)
	private Long id;
	
	/** The name. */
	@Size(min=2, max=40)
	@Column(name="name")
	private String name;
	
	/** The user name. */
	@Size(min=4, max=20)
	@Column(name="username", nullable = false)
	private String userName;
	
	/** The email. */
	@Size(max=50)
	@Column(name="email", nullable = false, unique = true)
	private String email;
	
	/** The phone. */
	@Size(min=4, max=20)
	@Column(name="phone")
	private String phone;
	

}
