package shoppingCart.CartService.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import shoppingCart.CartService.model.Cart;
import shoppingCart.CartService.model.IdGenerator;
import shoppingCart.CartService.model.Item;
import shoppingCart.CartService.model.Product;
import shoppingCart.CartService.repository.CartRepository;
import shoppingCart.CartService.repository.IdRepository;

@RestController
@RequestMapping("/cart")
public class CartResource {

	@Autowired
	CartRepository cartRepository;
	@Autowired
	IdRepository idRepository;
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/{userId}")
	public Cart getCart(@PathVariable("userId") int userId) {
		List<Cart> carts = new ArrayList<>();
		cartRepository.findAll().stream().forEach(cart -> {           //getting Cart for a particular User
			if(cart.getUserId()==userId)
				carts.add(cart);
		});
		System.out.println(carts);
		return carts.get(0);
	}
	
	@PostMapping("/create/{userId}")
	public void createCart(@PathVariable("userId") int userId) {
		IdGenerator idGen= idRepository.findById("cartId").get();
		int id= idGen.getSeq();                                       // Creating the cart for the new User
		idGen.setSeq(id+1);
		idRepository.save(idGen);
		cartRepository.save(new Cart(id, userId, new HashMap<Integer,Item>(), 0));
	}
	
	@PutMapping("/add/{userId}/{prodId}")
	public Cart addItem(@PathVariable("prodId") int prodId, @PathVariable("userId") int userId) {
		Product product= restTemplate.getForObject("http://product-service/product/"+prodId, Product.class);
		System.out.println(product);
		Cart cart= getCart(userId);
		Map<Integer,Item> items= cart.getItems();
		
		Item i= items.get(prodId);                                   //adding a product by id to cart using User by Id
		if(items.containsKey(prodId)) {
			i.setQuantity(i.getQuantity()+1);
			i.setPrice(product.getPrice()*i.getQuantity());
			items.put(prodId, i);
		}
		else {
			items.put(prodId, new Item(prodId, product.getImage(), product.getName(), product.getPrice(), 1));
		}
		cart.setItems(items);
		cart.setTotal(0);
		items.values().forEach(item -> cart.setTotal(cart.getTotal()+item.getPrice()));
		cartRepository.save(cart);
		return cart;
	}
	
	@PutMapping("/remove/{userId}/{prodId}")
	public Cart removeItem(@PathVariable("prodId") int prodId, @PathVariable("userId") int userId) {
		Product product= restTemplate.getForObject("http://product-service/product/"+prodId, Product.class);
		System.out.println(product);
		Cart cart= getCart(userId);
		Map<Integer, Item> items= cart.getItems();
		Item i= items.get(prodId);                                   //Removing a product by id to cart using User by Id
		if(i.getQuantity()>1) {
			i.setQuantity(i.getQuantity()-1);
			i.setPrice(product.getPrice()*i.getQuantity());
			items.put(prodId, i);
		}
		else {
			items.remove(prodId);
		}
		cart.setItems(items);
		cart.setTotal(0);
		items.values().forEach(item -> cart.setTotal(cart.getTotal()+item.getPrice()));
		cartRepository.save(cart);
		return cart;
	}
	
	@GetMapping("/items/{userId}")
	public Collection<Item> getItems(@PathVariable("userId") int userId){
		return getCart(userId).getItems().values();                   //Getting Items List for a particular User
	}
	
	@GetMapping("/cartItems/{userId}")
	public int noOfCartItems(@PathVariable("userId") int userId) {
		Cart cart= getCart(userId);
		int total=0;
		for(Item item: cart.getItems().values()) {
			total+=item.getQuantity();                               //Number Of items present in Cart
		}
		return total;
	}
	
	
	@GetMapping("/all")
	public List<Cart> getAllCarts(){
		List<Cart> carts= new ArrayList<>();
		cartRepository.findAll().stream().forEach(carts::add);         //Getting all the Carts present in Data Base
		return carts;
	}
	
	@PutMapping("/clear/{userId}")
	public void deleteCart(@PathVariable("userId") int userId) {
		Cart cart= getCart(userId);
		cart.setItems(new HashMap<Integer, Item>());
		cart.setTotal(0);                                            //Clearing all the items present in cart of User
		cartRepository.save(cart);
	}
	
}
