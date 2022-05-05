package shoppingCart.CartService.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	int id;
	String image;
	String prodName;
	double price;
	int quantity;
}
