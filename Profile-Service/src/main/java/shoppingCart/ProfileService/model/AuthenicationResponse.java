package shoppingCart.ProfileService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenicationResponse {
	private String jwt;
	private int userId;
	private String name;
}
