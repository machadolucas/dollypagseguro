package uol.pagseguro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uol.pagseguro.entity.PayingNotificationEntity;

import java.util.List;

/**
 * Created by machadolucas on 01/11/16.
 */
public interface PayingNotificationRepository extends MongoRepository<PayingNotificationEntity, String> {

    List<PayingNotificationEntity> findAllBySellerEmail(String sellerEmail);
}
