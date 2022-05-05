package shoppingCart.CartService.model;



import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Product {
	
	@Id
	 int id;
	private String productName;
	private String productType;
	private String productCategory;
	private String productDescription;
	private double productPrice;
	private String image;
	
}
