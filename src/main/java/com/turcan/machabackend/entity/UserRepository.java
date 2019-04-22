package com.turcan.machabackend.entity;

import org.springframework.data.repository.CrudRepository;
//JPA
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}