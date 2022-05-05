package shoppingCart.orderService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import shoppingCart.orderService.model.IdGenerator;

public interface IdRepository extends MongoRepository<IdGenerator, String> {

}
