package br.ufpe.cin.transformationserver.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;

import br.ufpe.cin.transformationserver.domain.User;

public interface UserRepository extends RevisionRepository<User, Long, Integer>,  CrudRepository<User, Long>{
	User findByEmail(String email);
}
