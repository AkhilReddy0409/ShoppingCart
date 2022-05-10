package shoppingCart.ProductService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document("product")
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