package com.codebrothers.services.auth.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codebrothers.services.auth.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByNameAndEmail(String name, String email);	
	List<User> findByName(String name);
}

