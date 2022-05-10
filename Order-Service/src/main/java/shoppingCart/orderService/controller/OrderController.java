package shoppingCart.orderService.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shoppingCart.orderService.model.IdGenerator;
import shoppingCart.orderService.model.Item;
import shoppingCart.orderService.model.Order;
import shoppingCart.orderService.repository.IdRepository;
import shoppingCart.orderService.repository.OrderRepository;



@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	IdRepository idRepository;
	
	@GetMapping("/all")
	public List<Order> getAllOrders(){
		List<Order> orders= new ArrayList<>();
		orderRepository.findAll().forEach(orders::add);
		return orders;
	}
	
	@PostMapping("/add")
	public void addOrder(@RequestBody Order order) {
		IdGenerator idGen= idRepository.findById("orderId").get();
		order.setId(idGen.getSeq());
		idGen.setSeq(idGen.getSeq()+1);
		idRepository.save(idGen);
		order.setDate(LocalDate.now());
		orderRepository.save(order);
		System.out.println(order.getUserId());
	}
	@GetMapping("/all/{userId}")
	public List<Order> getAllOrders(@PathVariable("userId") int userId) {
		List<Order> orders = new ArrayList<>();
		orderRepository.findAll().stream().forEach(order ->{
			if(order.getUserId()==userId)
				orders.add(order);
		});
		Collections.reverse(orders);
		return orders;
	}
	@GetMapping("/get/{orderId}")
	public Order getOrder(@PathVariable("orderId") int orderId) {
		return orderRepository.findById(orderId).get();
	}
	
	@GetMapping("/recent/{userId}")
	public Order getRecentOrder(@PathVariable("userId") int userId) {
		List<Order> orders= getAllOrders(userId);
		return orders.get(0);
	}
	

	@GetMapping("/recent/items/{userId}")
	public Collection<Item> getRecentItems(@PathVariable("userId") int userId){
		return getRecentOrder(userId).getItems().values();
	}
	
	@GetMapping("/items/{orderId}")
	public Collection<Item> getItems(@PathVariable("orderId") int orderId) {
		return getOrder(orderId).getItems().values();
	}
	
	
	
	
	
	
}
