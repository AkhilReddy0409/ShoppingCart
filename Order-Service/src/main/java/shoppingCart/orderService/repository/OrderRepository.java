package shoppingCart.orderService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import shoppingCart.orderService.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, Integer>{

}
