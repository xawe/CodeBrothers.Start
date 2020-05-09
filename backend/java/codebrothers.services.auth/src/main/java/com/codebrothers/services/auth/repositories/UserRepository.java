package com.codebrothers.services.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codebrothers.services.auth.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

