package shoppingCart.ProfileService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import shoppingCart.ProfileService.model.IdGenerator;

@Repository
public interface IdRepository extends MongoRepository<IdGenerator, String> {

}
