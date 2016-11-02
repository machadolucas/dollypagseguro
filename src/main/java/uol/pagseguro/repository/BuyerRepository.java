package uol.pagseguro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uol.pagseguro.entity.BuyerEntity;

/**
 * Created by machadolucas on 01/11/16.
 */
public interface BuyerRepository extends MongoRepository<BuyerEntity, String> {

    BuyerEntity findByEmail(String email);
}
