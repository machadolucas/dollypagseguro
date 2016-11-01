package uol.pagseguro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uol.pagseguro.entity.ComandaEntity;
import uol.pagseguro.entity.SellerEntity;

import java.util.List;

/**
 * Created by machadolucas on 01/11/16.
 */
public interface ComandaRepository extends MongoRepository<ComandaEntity, String> {

    List<ComandaEntity> findAllBySeller(SellerEntity seller);

    ComandaEntity findBySellerAndIdComanda(SellerEntity seller, String idComanda);
}
