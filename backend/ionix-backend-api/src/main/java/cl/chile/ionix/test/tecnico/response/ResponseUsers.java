package cl.chile.ionix.test.tecnico.response;

import java.io.Serializable;
import java.util.List;

import cl.chile.ionix.test.tecnico.dto.UsersDTO;
import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new response users.
 */
@Data
public class ResponseUsers implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8928331031117068599L;
	
	/** The status. */
	private StatusResponse status;
	
	/** The data. */
	private List<UsersDTO> data;

}
