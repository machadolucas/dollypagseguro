package uol.pagseguro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uol.pagseguro.entity.SellerEntity;
import uol.pagseguro.repository.SellerRepository;

/**
 * Created by machadolucas on 02/11/16.
 */
@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public void createSeller(final String email, final String name, final String publicKey) throws Exception {

        SellerEntity sellerEntity = this.sellerRepository.findByEmail(email);

        if (sellerEntity == null) {
            sellerEntity = SellerEntity.builder().email(email).name(name).publicKey(publicKey).build();
            this.sellerRepository.save(sellerEntity);
        } else {
            throw new Exception();
        }
    }
}
