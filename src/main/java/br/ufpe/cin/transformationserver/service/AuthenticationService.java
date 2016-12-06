package br.ufpe.cin.transformationserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.ufpe.cin.transformationserver.domain.User;

@Service
public class AuthenticationService implements UserDetailsService{

	@Autowired
	private UserService userService;

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		List<GrantedAuthority> listGrantAuthority = new ArrayList<GrantedAuthority>();
		listGrantAuthority.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		User user = userService.findByEmail(email);
		
		UserDetails userDetails = null;

		if(user!=null){
			boolean accountNonLocked=true;
			boolean enabledUser=true;
			boolean accountNonExpired=true;
			boolean credentialsNonExpired=true;
			userDetails = new  org.springframework.security.core.userdetails.User(email, user.getPassword(), enabledUser, accountNonExpired, credentialsNonExpired, accountNonLocked, listGrantAuthority);
		}     
		return userDetails;
	}


}
