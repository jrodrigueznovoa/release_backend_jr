package cl.chile.ionix.test.tecnico.response;

import java.io.Serializable;

import lombok.Data;

/**
 * Instantiates a new detail.
 */
@Data
public class Detail implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4431623322151697206L;
	
	/** The email. */
	private String email;
	
	/** The phone number. */
	private String phoneNumber;

}
