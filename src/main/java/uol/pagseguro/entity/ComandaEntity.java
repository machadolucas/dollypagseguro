package uol.pagseguro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by machadolucas on 01/11/16.
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class ComandaEntity {

    @Id
    private ObjectId id;

    private String idComanda;
    private BigDecimal value;

    @DBRef
    private ImageEntity imageFinal;

    @DBRef
    private SellerEntity seller;

    @DBRef
    private List<BuyerEntity> buyers;

    @DBRef
    private List<ProductEntity> products;

    private ComandaStatus status = ComandaStatus.BLANK;

    @AllArgsConstructor
    public enum ComandaStatus {

        BLANK(0, "blank"), //
        OPEN(1, "open"), //
        CLOSING(2, "closing"), //
        PAYING(3, "paying"), //
        PAID(4, "paid"), //
        CLOSED(5, "closed"), //
        REFUSED(6, "refused");

        private int code;
        private String name;

        @Override
        public String toString() {
            return this.name;
        }
    }
}
