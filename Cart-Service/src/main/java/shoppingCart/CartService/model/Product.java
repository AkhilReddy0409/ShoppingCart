package shoppingCart.CartService.model;

import org.springframework.data.annotation.Id;
import lombok.Data;

@Data
public class Product {
	@Id
	 int id;
	private String name;
	private String productType;
	private String category;
	private String description;
	private String image;
	private double price;
	
	}
