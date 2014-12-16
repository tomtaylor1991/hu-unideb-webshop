package hu.unideb.webshop.dao;

import hu.unideb.webshop.dto.UserDTO;
import hu.unideb.webshop.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends PagingAndSortingRepository<User, Long>,
		BaseConvertDao<User, UserDTO> {

	User findUserByLogin(String login);

	Page<User> findByLoginStartsWith(String filter, Pageable pageable);

}
