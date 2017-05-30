package com.bonzai.repository;

import com.bonzai.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value="select u.name from User u where u.team = ?#{[0]}")
	List<User> findUserByTeam(String team);

	User findUserByName(String email);
	
	@Modifying
	@Query(value="update User u SET u.password = ?1 where u.id = ?2")
	void updateUserPassord( String pass,long id);
}
																																																																 