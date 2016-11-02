package uol.pagseguro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uol.pagseguro.entity.PaidNotificationEntity;

import java.util.List;

/**
 * Created by machadolucas on 01/11/16.
 */
public interface PaidNotificationRepository extends MongoRepository<PaidNotificationEntity, String> {

    List<PaidNotificationEntity> findAllBySellerEmail(String sellerEmail);
}
