package shoppingCart.ProductService.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shoppingCart.ProductService.model.Product;
import shoppingCart.ProductService.repository.ProductRepository;
import shoppingCart.ProductService.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductResource {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	ProductRepository productRepository;
	
	@PostMapping("/addProduct")
	public String addProduct(@RequestBody Product p){                           //adding new product into Database
		return productService.addProduct(p);
	}
	
	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts(){                                      //Read all products in the Database
		return productService.getAllProducts();
	}
	
	@GetMapping("/{productId}")
	public Optional<Product> getProductById(@PathVariable int productId){       //Read By product ID in the Database
		return productService.getProductById(productId);
	}
	
	@PutMapping("/update")
	public void updateProduct(@RequestBody Product p) {                         //update a product into Database
		productService.updateProduct(p);
	}
	
	
	
	 @DeleteMapping("/deleteProduct/{productId}")
	 public String deleteProduct(@PathVariable int productId){                  //deleting a product in Database
			return productService.deleteProduct(productId);
		}
	 
		
	
	
	

}