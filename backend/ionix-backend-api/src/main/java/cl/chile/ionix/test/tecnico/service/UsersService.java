package cl.chile.ionix.test.tecnico.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.chile.ionix.test.tecnico.dto.UsersDTO;
import cl.chile.ionix.test.tecnico.model.UsersModel;
import cl.chile.ionix.test.tecnico.repository.IUsersRepository;
import cl.chile.ionix.test.tecnico.response.ResponseDefault;
import cl.chile.ionix.test.tecnico.response.ResponseUser;
import cl.chile.ionix.test.tecnico.response.ResponseUsers;
import cl.chile.ionix.test.tecnico.response.StatusResponse;
import cl.chile.ionix.test.tecnico.util.Constants;

/**
 * The Class UsersService.
 */
@Service
public class UsersService {
	
	/** The usr repository. */
	@Autowired
	private IUsersRepository usrRepository;
	
	/**
	 * List users.
	 *
	 * @return the response users
	 */
	public ResponseUsers listUsers() {
		ResponseUsers response = new ResponseUsers();
		StatusResponse status = new StatusResponse();

        try {

			List<UsersModel> listUsers = usrRepository.findAllByOrderByUserNameAsc();
			List<UsersDTO> listUsersDto = new ArrayList<UsersDTO>();
			
            if(!listUsers.isEmpty() && listUsers != null ){
            	status = new StatusResponse(Constants.OK_200, Constants.OK);
                listUsers.forEach(user -> {
                	
                	UsersDTO userDto = mappingUsers(user);
                	listUsersDto.add(userDto);
                	
                });
                response.setData(listUsersDto);
                response.setStatus(status);
            }else {
            	status = new StatusResponse(Constants.OK_1204, Constants.QUERY_EMPTY);
            	response.setStatus(status);
            }
   
        } catch (Exception e) {
        	status = new StatusResponse(Constants.ERROR_BD, Constants.QUERY_ERROR);
        	response.setStatus(status);
        }

        return response;
	}
	
	/**
	 * Mapping users.
	 *
	 * @param user the user
	 * @return the users DTO
	 */
	private UsersDTO mappingUsers(UsersModel user) {
		
		UsersDTO userDto = new UsersDTO();
		userDto.setIdUser(user.getId());
    	userDto.setName(user.getName());
    	userDto.setUserName(user.getUserName());
    	userDto.setEmail(user.getEmail());
    	userDto.setPhone(user.getPhone());
    	
    	return userDto;

	}
	
	/**
	 * Creates the user.
	 *
	 * @param user the user
	 * @return the response default
	 */
	public ResponseDefault createUser(UsersDTO user) {
		ResponseDefault response = new ResponseDefault();
		StatusResponse status = new StatusResponse();
		
		Optional<UsersModel> userModel = usrRepository.findByEmailEquals(user.getEmail());

		try {
			
			if(!userModel.isPresent()){
				
				UsersModel nuevoUsr = new UsersModel();
				nuevoUsr.setName(user.getName());
				nuevoUsr.setUserName(user.getUserName());
				nuevoUsr.setEmail(user.getEmail());
				nuevoUsr.setPhone(user.getPhone());
				
				usrRepository.save(nuevoUsr);
				response.setStatus(new StatusResponse(Constants.OK_200, Constants.REGISTRO_OK));
			}else{
				
				response.setStatus(new StatusResponse(Constants.COD_VALIDATE_EMAIL, Constants.MESSAGE_VALIDATE_EMAIL));
			}

		} catch (Exception e) {
			status = new StatusResponse(Constants.ERROR_BD, Constants.QUERY_ERROR);
        	response.setStatus(status);
		}

		return response;
	}
		
	
	/**
	 * Gets the user.
	 *
	 * @param email the email
	 * @return the user
	 */
	public ResponseUser getUser(String email) {
		ResponseUser response = new ResponseUser();
		StatusResponse status = new StatusResponse();
		try {
			
			Optional<UsersModel> userModel = usrRepository.findByEmailEquals(email);
			
			
			if(userModel.isPresent()) {
				UsersModel userM = userModel.get();
				UsersDTO user = mappingUsers(userM);
				/*user.setIdUser(userM.getId());
				user.setName(userM.getName());
				user.setUserName(userM.getUserName());
				user.setEmail(userM.getEmail());
				user.setPhone(userM.getPhone());*/
				
				response.setStatus(new StatusResponse(Constants.OK_200, Constants.OK));
				response.setData(user);
                
				
			}else {
				status = new StatusResponse(Constants.OK_1204, Constants.MESSAGE_NOT_USER.concat(email));
            	response.setStatus(status);
			}				
			
		} catch (Exception e) {
			status = new StatusResponse(Constants.ERROR_BD, Constants.QUERY_ERROR);
        	response.setStatus(status);	
		}
		return response;
		
	}
	
	/**
	 * Delete user.
	 *
	 * @param id the id
	 * @return the response default
	 */
	public ResponseDefault deleteUser(Long id) {
		ResponseDefault response = new ResponseDefault();
		StatusResponse status = new StatusResponse();

		try {
			
			Optional<UsersModel> userModel = usrRepository.findById(id);
			if(userModel.isPresent()) {
				usrRepository.deleteById(id);
				response.setStatus(new StatusResponse(Constants.OK_200, Constants.MESSAGE_DELETE));
			}else {
				response.setStatus(new StatusResponse(Constants.OK_1204, Constants.NOT_USER));
			}
			

		} catch (Exception e) {
			status = new StatusResponse(Constants.ERROR_BD, Constants.DELETE_ERROR);
        	response.setStatus(status);	
		}

		return response;
	}
	


}
