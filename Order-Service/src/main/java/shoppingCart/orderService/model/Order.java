package shoppingCart.orderService.model;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	@Id
	private int id;
	private int userId;
	private long amount;
	private Map<Integer, Item> items;
	private LocalDate date;
	private String status;
}
