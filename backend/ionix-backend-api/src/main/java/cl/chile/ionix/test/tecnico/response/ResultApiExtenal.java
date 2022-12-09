package cl.chile.ionix.test.tecnico.response;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * Instantiates a new result api extenal.
 */
@Data
public class ResultApiExtenal implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4635376221907931315L;
	
	/** The items. */
	List<Items> items;
}
