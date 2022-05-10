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
	 int id;
	 String name;
	 String username;
	 String emailId;
	 long number;
	 String gender;
	 String role;
	 String password;
	 Address address;

}
