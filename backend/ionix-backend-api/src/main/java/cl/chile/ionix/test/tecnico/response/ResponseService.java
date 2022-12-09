package cl.chile.ionix.test.tecnico.response;

import java.io.Serializable;

import lombok.Data;

/**
 * Instantiates a new response service.
 */
@Data
public class ResponseService implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6235440074503870912L;
	
	/** The response code. */
	private String responseCode;
	
	/** The description. */
	private String description;
	
	/** The elapsed time. */
	private Long elapsedTime;
	
	/** The result. */
	private ResultService result;
	

}
