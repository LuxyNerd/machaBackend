package com.turcan.machabackend.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//JPA
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);

	User getById(Long id);
}