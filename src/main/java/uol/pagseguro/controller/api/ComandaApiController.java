package uol.pagseguro.controller.api;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uol.pagseguro.entity.ComandaEntity;
import uol.pagseguro.service.ComandaService;
import uol.pagseguro.service.NotificationService;
import uol.pagseguro.vo.*;

import java.util.List;

/**
 * Created by machadolucas on 01/11/16.
 */
@Api
@RestController
public class ComandaApiController {

    @Autowired
    private ComandaService comandaService;

    @Autowired
    private NotificationService notificationService;

    /**
     * Abrir a comanda
     *
     * @param openComandaRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/comanda")
    public ResponseEntity<OpenComandaResponse> createComanda(@RequestBody final OpenComandaRequest openComandaRequest) {

        final List<String> activeBuyers = this.comandaService.createComanda(openComandaRequest.getIdComanda(),
                openComandaRequest.getSellerEmail(), openComandaRequest.getBuyerEmail());

        final OpenComandaResponse openComandaResponse = OpenComandaResponse.builder().activeBuyers(activeBuyers)
                .build();
        return new ResponseEntity<>(openComandaResponse, HttpStatus.OK);
    }

    /**
     * Listar brevemente as comandas abertas de um vendedor
     *
     * @param comandasListRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/comanda/list")
    public ResponseEntity<ComandasListResponse> listComandas(@RequestBody final ComandasListRequest
                                                                         comandasListRequest) {

        final List<ComandaVO> comandas = this.comandaService.listComandas(comandasListRequest.getSellerEmail());

        final ComandasListResponse comandasListResponse = ComandasListResponse.builder().comandas(comandas).build();

        return new ResponseEntity<>(comandasListResponse, HttpStatus.OK);
    }

    /**
     * Obter detalhes de uma comanda
     *
     * @param comandaDetailRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/comanda/get")
    public ResponseEntity<ComandaDetailResponse> getComandaDetail(@RequestBody final ComandaDetailRequest
                                                                              comandaDetailRequest) {

        final ComandaEntity comanda = this.comandaService.getComanda(comandaDetailRequest.getSellerEmail(),
                comandaDetailRequest.getIdComanda());

        final ComandaDetailResponse comandaDetailResponse = ComandaDetailResponse.builder().comanda(comanda).build();
        return new ResponseEntity<>(comandaDetailResponse, HttpStatus.OK);
    }

    /**
     * Comprador solicita o fechamento de uma comanda
     *
     * @param closingComandaRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/comanda/closing")
    public ResponseEntity<ClosingComandaResponse> closingComanda(@RequestBody final ClosingComandaRequest closingComandaRequest) throws Exception {

        this.comandaService.closingComanda(closingComandaRequest.getIdComanda(), closingComandaRequest.getSellerEmail
                (), closingComandaRequest.getBuyerEmail());

        final ClosingComandaResponse closingComandaResponse = ClosingComandaResponse.builder().build();
        return new ResponseEntity<>(closingComandaResponse, HttpStatus.OK);
    }

    /**
     * Vendedor fecha a comanda e solicita o pagamento aos compradores
     *
     * @param closeToPaymentComandaRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/comanda/closeToPayment")
    public ResponseEntity<CloseToPaymentComandaResponse> closeToPaymentComanda(@RequestBody final
                                                                                   CloseToPaymentComandaRequest
                                                                                           closeToPaymentComandaRequest) throws Exception {

        this.comandaService.closeToPayment(closeToPaymentComandaRequest.getIdComanda(), closeToPaymentComandaRequest
                .getSellerEmail(), closeToPaymentComandaRequest.getValue(), closeToPaymentComandaRequest.getImage());

        final CloseToPaymentComandaResponse closeToPaymentComandaResponse = CloseToPaymentComandaResponse.builder()
                .build();
        return new ResponseEntity<>(closeToPaymentComandaResponse, HttpStatus.OK);
    }

    /**
     * Comprador fez o pagamento e notifica resultado
     *
     * @param paidComandaRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/comanda/paid")
    public ResponseEntity<PaidComandaResponse> paymentDone(@RequestBody final PaidComandaRequest paidComandaRequest)
            throws Exception {

        this.comandaService.processPaidComanda(paidComandaRequest.getIdComanda(), paidComandaRequest.getSellerEmail()
                , paidComandaRequest.getValue(), paidComandaRequest.getTransactionCode(), paidComandaRequest
                        .getStatus());

        final PaidComandaResponse paidComandaResponse = PaidComandaResponse.builder().build();
        return new ResponseEntity<>(paidComandaResponse, HttpStatus.OK);
    }

    /**
     * Vendedor muda o status da comanda para CLOSED
     *
     * @param finalizeComandaRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/comanda/finalize")
    public ResponseEntity<FinalizeComandaResponse> finalizeComanda(@RequestBody final FinalizeComandaRequest
                                                                               finalizeComandaRequest) throws
            Exception {

        this.comandaService.finalizeComanda(finalizeComandaRequest.getIdComanda(), finalizeComandaRequest
                .getSellerEmail());

        final FinalizeComandaResponse finalizeComandaResponse = FinalizeComandaResponse.builder().build();
        return new ResponseEntity<>(finalizeComandaResponse, HttpStatus.OK);
    }


}
