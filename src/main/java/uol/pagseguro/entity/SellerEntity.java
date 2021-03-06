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
public class SellerEntity {

    @Id
    private ObjectId id;

    private String name;
    private String email;
    private String publicKey;
}
