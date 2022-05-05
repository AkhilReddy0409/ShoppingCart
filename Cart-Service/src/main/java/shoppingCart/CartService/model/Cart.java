package shoppingCart.CartService.model;


import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	@Id
	private int id;
	private int userId;
	private Map<Integer, Item> items;
	private double total;
}