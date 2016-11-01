package uol.pagseguro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uol.pagseguro.entity.ProductEntity;

/**
 * Created by machadolucas on 01/11/16.
 */
public interface ProductRepository extends MongoRepository<ProductEntity, String> {
}
