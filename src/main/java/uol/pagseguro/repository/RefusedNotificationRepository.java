package uol.pagseguro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uol.pagseguro.entity.RefusedNotificationEntity;

import java.util.List;

/**
 * Created by machadolucas on 01/11/16.
 */
public interface RefusedNotificationRepository extends MongoRepository<RefusedNotificationEntity, String> {

    List<RefusedNotificationEntity> findAllBySellerEmail(String sellerEmail);
}
