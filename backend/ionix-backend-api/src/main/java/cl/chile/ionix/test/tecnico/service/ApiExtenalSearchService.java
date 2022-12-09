package cl.chile.ionix.test.tecnico.service;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.chile.ionix.test.tecnico.encrypt.EncryptDES;
import cl.chile.ionix.test.tecnico.response.ResponseExtenalApi;
import cl.chile.ionix.test.tecnico.response.ResponseService;
import cl.chile.ionix.test.tecnico.response.ResultService;
import cl.chile.ionix.test.tecnico.util.Constants;
import lombok.extern.log4j.Log4j2;

/** The Constant log. */
@Log4j2
@Service
public class ApiExtenalSearchService {
	
	/** The api external. */
	@Value("${api.extenal-test}")
	private String apiExternal;
	
	/** The key. */
	@Value("${api.extenal-key}")
	private String key;
	
	/**
	 * Information api external.
	 *
	 * @param rut the rut
	 * @return the response service
	 */
	public ResponseService informationApiExternal(String rut) {
		ResponseService response = new ResponseService();
		
		long milliSeconds = Timestamp.valueOf(LocalDateTime.now()).getTime();
		ResponseExtenalApi respApiExtenal = searchByRutEncrypt(rut);
		long milliSeconds2 = Timestamp.valueOf(LocalDateTime.now()).getTime();
		try {

			long elapsedTime = milliSeconds2 - milliSeconds;
			Long countItems = respApiExtenal.getResult().getItems().stream().count();
	
			if(respApiExtenal.getResponseCode().equals("200")) {
				ResultService result = new ResultService();
				response.setResponseCode(Constants.COD_RESPONSE_SERVICE);
				response.setDescription(Constants.OK);
				response.setElapsedTime(elapsedTime);
				result.setRegisterCount(countItems);
				response.setResult(result);
			}
			
		} catch (Exception e) {
			response.setResponseCode(Constants.COD_EXTERNAL_SERVICE);
			response.setDescription(respApiExtenal.getDescription());
		}
		return response;
	}
	
	/**
	 * Search by rut encrypt.
	 *
	 * @param rut the rut
	 * @return the response extenal api
	 */
	private ResponseExtenalApi searchByRutEncrypt(String rut) {
		ResponseExtenalApi response = new ResponseExtenalApi();
		try {
			String plainRut = EncryptDES.encryptRut(rut, key);
			Map<String, String> pathVariables = new HashMap<>();
			pathVariables.put("parametro", plainRut.toString());
			
			HttpHeaders headers = new HttpHeaders();
			ResponseEntity<ResponseExtenalApi> exchange1 = null;
			headers.add("X-API-Key", "f2f719e0");
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
			
			exchange1 = restTemplate.exchange(apiExternal, HttpMethod.GET, requestEntity, ResponseExtenalApi.class, pathVariables);
			response = exchange1.getBody();
				
		}catch (Exception e) {
			
			response.setDescription(Constants.MESSAGE_RESPONSE_SERVICE +  e.getMessage());
			log.error(e.getMessage());
	
		}

		return response;
		
		
	}

}
