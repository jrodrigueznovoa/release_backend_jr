package cl.chile.ionix.test.tecnico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.chile.ionix.test.tecnico.response.ResponseService;
import cl.chile.ionix.test.tecnico.service.ApiExtenalSearchService;
import cl.chile.ionix.test.tecnico.util.Constants;
import lombok.extern.log4j.Log4j2;


/**
 * The Class ApiExternalController.
 */
@RestController

/** The Constant log. */
@Log4j2
@CrossOrigin(origins = "*", methods = { RequestMethod.POST })
@RequestMapping(value="/search")
public class ApiExternalController {
	
	/** The service. */
	@Autowired
	private ApiExtenalSearchService service;
	
	/**
	 * Information api external.
	 *
	 * @param plainRut the plain rut
	 * @return the response entity
	 */
	@PostMapping(value = "/external/{plainRut}", produces = "application/json")
	public ResponseEntity<ResponseService> informationApiExternal(@PathVariable("plainRut") String plainRut) {
		log.info("-- Init endpoint informationApiExternal --");
		ResponseService response = new ResponseService();
		try {
			response = service.informationApiExternal(plainRut);
		} catch (Exception e) {
			response.setResponseCode(Constants.COD_SERVICE);
			response.setDescription(Constants.ERROR_SERVICE);

		}

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
