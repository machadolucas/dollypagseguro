package uol.pagseguro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uol.pagseguro.entity.ImageEntity;

/**
 * Created by machadolucas on 01/11/16.
 */
public interface ImageRepository extends MongoRepository<ImageEntity, String> {
}
