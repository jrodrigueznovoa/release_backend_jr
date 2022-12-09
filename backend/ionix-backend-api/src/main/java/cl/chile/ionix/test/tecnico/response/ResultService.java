package cl.chile.ionix.test.tecnico.response;

import java.io.Serializable;

import lombok.Data;

/**
 * Instantiates a new result service.
 */
@Data
public class ResultService implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1747820064651278747L;
	
	/** The register count. */
	private Long registerCount;

}
