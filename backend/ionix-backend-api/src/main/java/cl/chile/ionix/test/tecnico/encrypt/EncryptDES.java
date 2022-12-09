package cl.chile.ionix.test.tecnico.encrypt;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import lombok.extern.log4j.Log4j2;

/** The Constant log. */
@Log4j2
public class EncryptDES {
	
	/**
	 * Encrypt rut.
	 *
	 * @param plainRut the plain rut
	 * @param key the key
	 * @return the string
	 */
	public static String encryptRut(String plainRut, String key) {
		String encryptedRut = null;
		try{      	
        	DESKeySpec keySpec = new DESKeySpec(key.getBytes("UTF8"));
        	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        	SecretKey secretKey = keyFactory.generateSecret(keySpec);
        	byte[] cleartext = plainRut.getBytes("UTF8");
        	Cipher cipher = Cipher.getInstance("DES");
        	cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        	encryptedRut = Base64.getEncoder().encodeToString(cipher.doFinal(cleartext));
        	

		}catch(Exception e) {
			log.error("Ha ocurrido un error en encryptRut: "+e.getMessage(), e);
		}
		
		return encryptedRut; 
	}

}
