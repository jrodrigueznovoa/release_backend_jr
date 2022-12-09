package cl.chile.ionix.test.tecnico.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * Instantiates a new users DTO.
 */
@Data
public class UsersDTO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4431086057909097786L;
	
	/** The id user. */
	private Long idUser;
	
	/** The name. */
	private String name;
	
	/** The user name. */
	private String userName;
	
	/** The email. */
	private String email;
	
	/** The phone. */
	private String phone;

}
