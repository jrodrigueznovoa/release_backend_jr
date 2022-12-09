package cl.chile.ionix.test.tecnico.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data

/**
 * Instantiates a new status response.
 *
 * @param code the code
 * @param description the description
 */
@AllArgsConstructor

/**
 * Instantiates a new status response.
 */
@NoArgsConstructor
public class StatusResponse implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3154259767149802186L;
	
	/** The code. */
	private String code;
	
	/** The description. */
	private String description;

}
