package shoppingCart.ProductService.repository;






import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import shoppingCart.ProductService.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer>{

	//Configuring the Repository 
	
}