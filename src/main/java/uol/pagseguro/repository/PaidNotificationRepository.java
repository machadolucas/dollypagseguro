package uol.pagseguro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uol.pagseguro.entity.PaidNotificationEntity;

/**
 * Created by machadolucas on 01/11/16.
 */
public interface PaidNotificationRepository extends MongoRepository<PaidNotificationEntity, String> {
}
