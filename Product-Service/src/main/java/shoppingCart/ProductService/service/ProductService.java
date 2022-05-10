package shoppingCart.ProductService.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoppingCart.ProductService.model.IdGenerator;
import shoppingCart.ProductService.model.Product;
import shoppingCart.ProductService.repository.IdRepository;
import shoppingCart.ProductService.repository.ProductRepository;


@Service
public class ProductService {

	@Autowired
    ProductRepository productRepo;
	
	@Autowired
	IdRepository idRepository;

	public String addProduct(Product product) {
		IdGenerator idGen= idRepository.findById("prodId").get();
		product.setId(idGen.getSeq());
		idGen.setSeq(idGen.getSeq()+1);
		idRepository.save(idGen);
		productRepo.save(product);                       //implementing save method from MongoRepository
		return "Product Added";
	}

	public List<Product> getAllProducts() {
		List<Product> products=productRepo.findAll();    //implementing Find method from MongoRepository
		return products;
	}

	 public String updateProduct(Product product) {

	        Optional<Product> prod = productRepo.findById(product.getId());
	        if (!prod.isPresent()) {
	            return ("Updation FAILED");
	        }
                                                                     //Update Method
	         productRepo.save(product);

	        return "Updation SUCCESS";
	    }
	 
	public Optional<Product> getProductById(int id) {
	        return productRepo.findById(id);	         //implementing Find By ID method from MongoRepository
	        }

	public String deleteProduct(int id) {
		productRepo.deleteById(id);                       //implementing Delete By ID method from MongoRepository
		return "deleted succesfully";
	}
	}