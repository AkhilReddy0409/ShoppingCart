package shoppingCart.ProfileService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="profile")
public class UserProfile {
	
	@Id
	 int profileId;
	 String fullName;
	 String emailId;
	 long moblieNumber;
	 String gender;
	 String role;
	 String password;

}
