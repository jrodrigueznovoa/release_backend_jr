package cl.chile.ionix.test.tecnico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.chile.ionix.test.tecnico.model.UsersModelToken;

/**
 * The Interface IUserTokenRepository.
 */
public interface IUserTokenRepository extends JpaRepository<UsersModelToken, Long>{
	
	/**
	 * Find one by user name.
	 *
	 * @param username the username
	 * @return the users model token
	 */
	UsersModelToken findOneByUserName(String username);

}
