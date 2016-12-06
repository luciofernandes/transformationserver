package br.ufpe.cin.transformationserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;

import br.ufpe.cin.transformationserver.domain.User;
import br.ufpe.cin.transformationserver.domain.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public Iterable<User> findAll(){
		Iterable<User> lista =  userRepository.findAll();
		return lista;
	}
	
	public User save(User user){
		User out = userRepository.save(user);
		return out;
	}
	public User findById(Long id) {
		return userRepository.findOne(id);
	}

	public void delete(Long id) {
		userRepository.delete(id);
	}
	
	public Revisions<Integer, User> findRevisions(Long id){
		return userRepository.findRevisions(id);
	}
}
