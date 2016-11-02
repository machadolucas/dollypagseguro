package uol.pagseguro.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;

/**
 * Created by machadolucas on 01/11/16.
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class TransactionEntity {

    @Id
    private ObjectId id;

    private String code;
    private String status;
    private BigDecimal value;

    @DBRef
    private ComandaEntity comandaEntity;
}
