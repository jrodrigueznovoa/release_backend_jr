package cl.chile.ionix.test.tecnico.response;

import java.io.Serializable;

import lombok.Data;


/**
 * Instantiates a new response extenal api.
 */
@Data
public class ResponseExtenalApi implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6168138892857983132L;
	
	/** The response code. */
	private String responseCode;
	
	/** The description. */
	private String description;
	
	/** The result. */
	ResultApiExtenal result;
	

}
