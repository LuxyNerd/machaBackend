package com.turcan.machabackend.entity;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<Test, Long> {
	List<Test> findByOwner(User owner);
}
