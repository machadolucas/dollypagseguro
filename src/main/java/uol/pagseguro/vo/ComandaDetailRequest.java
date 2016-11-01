package uol.pagseguro.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by machadolucas on 01/11/16.
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComandaDetailRequest {

    private String idComanda;
    private String sellerEmail;
}
