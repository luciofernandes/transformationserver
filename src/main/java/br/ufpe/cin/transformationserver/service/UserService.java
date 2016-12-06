package br.ufpe.cin.transformationserver.service;

import org.springframework.data.history.Revisions;

import br.ufpe.cin.transformationserver.domain.User;

public interface UserService {
	public User findByEmail(String email);
	public Iterable<User> findAll();
	public User save(User user);
	public User findById(Long id);
	public void delete(Long id);
	public Revisions<Integer, User> findRevisions(Long id);
}
