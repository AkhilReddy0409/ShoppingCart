package shoppingCart.ProfileService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import shoppingCart.ProfileService.model.IdGenerator;
import shoppingCart.ProfileService.model.UserProfile;
import shoppingCart.ProfileService.repository.IdRepository;
import shoppingCart.ProfileService.repository.ProfileRepository;
import shoppingCart.ProfileService.service.ProfileService;


@RestController
@RequestMapping("/profile")
public class ProfileResource {
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	IdRepository idRepo;
	
	@PostMapping("/addProfile")
	public void addUser(@RequestBody UserProfile user) {
		 profileService.addProfile(user);
	}
	
	@GetMapping("/getAllProfiles")
	public List<UserProfile> getAllProfiles(){                                     //Read All profile into Database
		return profileService.getAllProfiles();
	}
	
	@GetMapping("/{id}")
	public UserProfile getUser(@PathVariable("id") int id) {
		return profileRepository.findById(id).get();
	}
	
	 @PutMapping("/updateProfile")
	    public String updateProfile(@RequestBody UserProfile profile) {           //Updating a profile into Database
	        return profileService.updateUserProfile(profile);
	    }
	
	
	 @DeleteMapping("/deleteProfile/{profileId}")
	 public String deleteProfile(@PathVariable int profileId){                   //Deleting a profile in Database
			return profileService.deleteById(profileId);
		}
	 
		
	
	
	

}