package uol.pagseguro.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uol.pagseguro.service.SellerService;
import uol.pagseguro.vo.CreateSellerRequest;
import uol.pagseguro.vo.CreateSellerResponse;

/**
 * Created by machadolucas on 01/11/16.
 */
@Api
@RestController
public class SellerController {

    @Autowired
    private SellerService sellerService;

    /**
     * Cria um vendedor no sistema
     *
     * @param createSellerRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/seller")
    public ResponseEntity<CreateSellerResponse> createSeller(@RequestBody final CreateSellerRequest
                                                                         createSellerRequest) throws
            Exception {

        this.sellerService.createSeller(createSellerRequest.getEmail(), createSellerRequest.getName(),
                createSellerRequest.getPublicKey());

        final CreateSellerResponse createSellerResponse = CreateSellerResponse.builder().build();
        return new ResponseEntity<>(createSellerResponse, HttpStatus.OK);
    }
}
