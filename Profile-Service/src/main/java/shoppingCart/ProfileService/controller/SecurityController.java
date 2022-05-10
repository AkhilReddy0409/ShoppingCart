package shoppingCart.ProfileService.controller;


import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shoppingCart.ProfileService.model.AuthenicationResponse;
import shoppingCart.ProfileService.model.AuthenticationRequest;
import shoppingCart.ProfileService.model.IdGenerator;
import shoppingCart.ProfileService.model.UserProfile;
import shoppingCart.ProfileService.repository.IdRepository;
import shoppingCart.ProfileService.repository.ProfileRepository;
import shoppingCart.ProfileService.security.JwtUtil;
import shoppingCart.ProfileService.service.SecurityService;

@RestController
@RequestMapping("/profile")
public class SecurityController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private SecurityService sService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	ProfileRepository profileRepo;
	
	@Autowired
	IdRepository idRepo;
	
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) throws Exception{
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username and password", e);
		}
		
		final UserDetails userDetails= sService.loadUserByUsername(authRequest.getUsername());
		 
		final String jwt= jwtUtil.generateToken(userDetails);
		System.out.println(jwt);
		
		UserProfile user= profileRepo.findByUsername(userDetails.getUsername());
		
		return 	ResponseEntity.ok(new AuthenicationResponse(jwt,user.getId(),user.getName()));
	}
	
	
	@PostMapping("/register")
	public String register(@RequestBody UserProfile user) {
		System.out.println(user);
		List<String> usernames= new ArrayList<>();
		profileRepo.findAll().forEach(u-> usernames.add(u.getUsername()));
		
		if(usernames.contains(user.getUsername()))
			{
			return "-1";
			}
		
		IdGenerator idGen= idRepo.findById("ProfileId").get();
		user.setId(idGen.getSeq());
		idGen.setSeq(idGen.getSeq()+1);
		idRepo.save(idGen);
		user.setRole("USER");
		profileRepo.save(user);
		return user.getId()+"";
		
	}
	
}
