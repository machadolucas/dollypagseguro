package uol.pagseguro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uol.pagseguro.entity.SellerEntity;

/**
 * Created by machadolucas on 01/11/16.
 */
public interface SellerRepository extends MongoRepository<SellerEntity, String> {

    SellerEntity findByEmail(String email);
}
