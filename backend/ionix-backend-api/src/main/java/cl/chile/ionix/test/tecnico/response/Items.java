package cl.chile.ionix.test.tecnico.response;

import java.io.Serializable;

import lombok.Data;


/**
 * Instantiates a new items.
 */
@Data
public class Items implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1121272012587067013L;
	
	/** The name. */
	private String name;
	
	/** The detail. */
	private Detail detail;
}
