package uol.pagseguro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uol.pagseguro.entity.ClosingNotificationEntity;

/**
 * Created by machadolucas on 01/11/16.
 */
public interface ClosingNotificationRepository extends MongoRepository<ClosingNotificationEntity, String> {
}
