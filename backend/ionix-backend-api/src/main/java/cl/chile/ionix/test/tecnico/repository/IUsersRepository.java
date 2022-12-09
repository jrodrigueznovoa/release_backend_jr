package cl.chile.ionix.test.tecnico.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.chile.ionix.test.tecnico.model.UsersModel;

/**
 * The Interface IUsersRepository.
 */
@Repository
public interface IUsersRepository extends JpaRepository<UsersModel, Long>{
	
	/**
	 * Find all by order by user name asc.
	 *
	 * @return the list
	 */
	List<UsersModel> findAllByOrderByUserNameAsc();
	
	/**
	 * Find by email equals.
	 *
	 * @param email the email
	 * @return the optional
	 */
	Optional<UsersModel> findByEmailEquals(String email);
}
