package uol.pagseguro.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Created by machadolucas on 01/11/16.
 */
@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaidComandaRequest {

    private String idComanda;
    private String sellerEmail;
    private String transactionCode;
    private BigDecimal value;
    private String status;

}
