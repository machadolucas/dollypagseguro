package uol.pagseguro.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

/**
 * Created by machadolucas on 01/11/16.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductEntity {

    @Id
    private ObjectId id;

    private String description;
    private BigDecimal value;
}
