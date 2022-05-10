package shoppingCart.ProfileService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoppingCart.ProfileService.model.IdGenerator;
import shoppingCart.ProfileService.model.UserProfile;
import shoppingCart.ProfileService.repository.IdRepository;
import shoppingCart.ProfileService.repository.ProfileRepository;

@Service
public class ProfileService {
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	IdRepository idRepository;
	
	public void addProfile(UserProfile userProfile ) {
		IdGenerator idGen= idRepository.findById("ProfileId").get();
		userProfile.setId(idGen.getSeq());
		idGen.setSeq(idGen.getSeq()+1);
		idRepository.save(idGen);
		userProfile.setRole("User");
		 profileRepository.save(userProfile);                       //implementing save method from MongoRepository
	}
	public List<UserProfile> getAllProfiles(){
		return profileRepository.findAll();                              //implementing Find method from MongoRepository
	}
	public UserProfile getByfullName(String fullName) {
		return profileRepository.findByUsername(fullName);              //implementing Find By FullName method from MongoRepository
	}
	
	public String updateUserProfile(UserProfile profile) {
		 Optional<UserProfile> prof = profileRepository.findById(profile.getId());
	        if (!prof.isPresent()) {
	            return ("Updation FAILED");
	        }
	        else {                                                      //implementing Update method from MongoRepository
	        profileRepository.save(profile);
	        return "Updation SUCCESS";
	    }}
	public void deleteAll() {
		profileRepository.deleteAll();                                  //implementing Delete ALL method from MongoRepository
	}
	
	public String deleteById(int profileId) {
		profileRepository.deleteById(profileId);                      //implementing Delete By ID method from MongoRepository
		return "Deleted";
	}
}





