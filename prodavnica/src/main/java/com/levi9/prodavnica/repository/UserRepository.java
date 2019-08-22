package com.levi9.prodavnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.levi9.prodavnica.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findOneByUsername(String username);

}
