package uol.pagseguro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uol.pagseguro.vo.*;

/**
 * Created by machadolucas on 01/11/16.
 */
@RestController
public class ComandaController {

    /**
     * Abrir a comanda
     *
     * @param openComandaRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/comanda")
    public ResponseEntity<OpenComandaResponse> createComanda(final OpenComandaRequest openComandaRequest) {

        return null;
    }

    /**
     * Listar brevemente as comandas abertas de um vendedor
     *
     * @param comandasListRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/comanda/list")
    public ResponseEntity<ComandasListResponse> listComandas(final ComandasListRequest comandasListRequest) {

        return null;
    }

    /**
     * Obter detalhes de uma comanda
     *
     * @param comandaDetailRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/comanda/get")
    public ResponseEntity<ComandaDetailResponse> getComandaDetail(final ComandaDetailRequest comandaDetailRequest) {

        return null;
    }

    /**
     * Comprador solicita o fechamento de uma comanda
     *
     * @param closeComandaRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/comanda/close")
    public ResponseEntity<CloseComandaResponse> closeComanda(final CloseComandaRequest closeComandaRequest) {

        return null;
    }


}
