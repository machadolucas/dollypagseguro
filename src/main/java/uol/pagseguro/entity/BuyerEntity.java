package uol.pagseguro.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * Created by machadolucas on 01/11/16.
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class BuyerEntity {

    @Id
    private ObjectId id;

    private String email;
    private String publicKey;
}
