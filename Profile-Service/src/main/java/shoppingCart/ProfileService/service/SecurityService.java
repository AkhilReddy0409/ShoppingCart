package shoppingCart.ProfileService.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import shoppingCart.ProfileService.model.UserProfile;
import shoppingCart.ProfileService.repository.ProfileRepository;



@Service
public class SecurityService implements UserDetailsService{
	
	@Autowired
	ProfileRepository profileRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserProfile uP= profileRepo.findByUsername(username);
		return new User(uP.getUsername(), uP.getPassword(), new ArrayList<>());
	}

}
