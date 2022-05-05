package shoppingCart.CartService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection="cartId")
@Data
public class IdGenerator {
	
	@Id
	String id;
	int seq;
}
