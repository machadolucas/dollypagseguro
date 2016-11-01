package uol.pagseguro.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Created by machadolucas on 01/11/16.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PayingNotificationEntity {

    @Id
    private ObjectId id;

    @DBRef
    private ComandaEntity comanda;
}
