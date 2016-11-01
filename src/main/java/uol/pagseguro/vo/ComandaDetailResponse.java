package uol.pagseguro.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uol.pagseguro.entity.ComandaEntity;

/**
 * Created by machadolucas on 01/11/16.
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComandaDetailResponse {

    public ComandaEntity comanda;

}
