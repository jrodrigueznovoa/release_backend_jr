package cl.chile.ionix.test.tecnico.response;

import java.io.Serializable;

import cl.chile.ionix.test.tecnico.dto.UsersDTO;
import lombok.Data;

/**
 * Instantiates a new response user.
 */
@Data
public class ResponseUser implements Serializable{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7266889802711545146L;
	
	/** The status. */
	private StatusResponse status;
	
	/** The data. */
	private UsersDTO data;


}
