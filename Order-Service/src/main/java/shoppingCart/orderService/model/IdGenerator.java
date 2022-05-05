package shoppingCart.orderService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection="orderId")
@Data
public class IdGenerator {

	@Id
	String id;
	int seq;
}
