package uol.pagseguro.vo;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uol.pagseguro.entity.ComandaEntity;

/**
 * Created by machadolucas on 01/11/16.
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class ComandaVO {

    private String idComanda;

    private ComandaEntity.ComandaStatus status;

}
