package shoppingCart.ProfileService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import shoppingCart.ProfileService.model.UserProfile;

@Repository
public interface ProfileRepository extends MongoRepository<UserProfile,Integer>{
public UserProfile findByfullName(String fullName);
public UserProfile findBymoblieNumber(long mobileNumber);        //configuring the Repository
public UserProfile findByprofileId(int profileId);
}
