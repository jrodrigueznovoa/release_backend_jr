package cl.chile.ionix.test.tecnico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Instantiates a new rol model.
 */
@Data
@Entity
@Table(name = "rol")
public class RolModel {
	
	/** The id rol. */
	@Id
	private Integer idRol;

	/** The nombre. */
	@Column(name = "nombre")
	private String nombre;

}
