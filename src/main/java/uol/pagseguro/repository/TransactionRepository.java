package uol.pagseguro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uol.pagseguro.entity.TransactionEntity;

/**
 * Created by machadolucas on 01/11/16.
 */
public interface TransactionRepository extends MongoRepository<TransactionEntity, String> {
}
