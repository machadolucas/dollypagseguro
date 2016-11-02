package uol.pagseguro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uol.pagseguro.entity.*;
import uol.pagseguro.repository.*;
import uol.pagseguro.vo.ComandaVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by machadolucas on 01/11/16.
 */
@Service
public class ComandaService {

    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    BuyerRepository buyerRepository;
    @Autowired
    ComandaRepository comandaRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    NotificationService notificationService;

    /**
     * Cria a comanda para o comprador e vendedor, ou adiciona o comprador na comanda ja existente.
     * Retorna a lista dos compradores naquela comanda.
     *
     * @param idComanda
     * @param sellerEmail
     * @param buyerEmail
     * @return
     */
    public List<String> createComanda(final String idComanda, final String sellerEmail, final String buyerEmail) {
        final SellerEntity sellerEntity = this.sellerRepository.findByEmail(sellerEmail);
        BuyerEntity buyerEntity = this.buyerRepository.findByEmail(buyerEmail);
        if (buyerEntity == null) {
            buyerEntity = BuyerEntity.builder().email(buyerEmail).build();
            this.buyerRepository.save(buyerEntity);
        }

        ComandaEntity comandaEntity = this.comandaRepository.findBySellerAndIdComanda(sellerEntity, idComanda);

        if (comandaEntity != null && comandaEntity.getStatus() == ComandaStatus.OPEN) {
            //Se comanda ja existir, adiciona o comprador nela
            final List<String> buyersEmails = comandaEntity.getBuyers().stream().map(BuyerEntity::getEmail).collect
                    (Collectors.toList());
            comandaEntity.getBuyers().add(buyerEntity);
            this.comandaRepository.save(comandaEntity);
            return buyersEmails;

        } else if (comandaEntity != null && comandaEntity.getStatus() == ComandaStatus.CLOSED) {
            //Se eh uma comanda existente e fechada, zera ela e reabre como nova
            final List<BuyerEntity> buyersList = new ArrayList<>();
            buyersList.add(buyerEntity);
            comandaEntity.setStatus(ComandaStatus.OPEN);
            comandaEntity.setBuyers(buyersList);
            comandaEntity.setImageFinal(null);
            comandaEntity.setValue(null);
            comandaEntity.setProducts(null);

            this.comandaRepository.save(comandaEntity);
            return buyersList.stream().map(BuyerEntity::getEmail).collect(Collectors.toList());

        } else {
            // Senao, cria a comanda com o primeiro comprador
            final List<BuyerEntity> buyersList = new ArrayList<>();
            buyersList.add(buyerEntity);
            comandaEntity = ComandaEntity.builder().idComanda(idComanda).seller(sellerEntity).buyers(buyersList)
                    .status(ComandaStatus.OPEN).build();
            this.comandaRepository.save(comandaEntity);
            return buyersList.stream().map(BuyerEntity::getEmail).collect(Collectors.toList());
        }
    }

    public List<ComandaVO> listComandas(final String sellerEmail) {
        final SellerEntity sellerEntity = this.sellerRepository.findByEmail(sellerEmail);
        final List<ComandaEntity> comandaEntities = this.comandaRepository.findAllBySeller(sellerEntity);
        return comandaEntities.stream().filter(this::canListComanda).map( //
                comandaEntity -> ComandaVO.builder().idComanda(comandaEntity.getIdComanda()).status(comandaEntity
                        .getStatus()).build())//
                .collect(Collectors.toList());
    }

    private boolean canListComanda(final ComandaEntity comandaEntity) {
        return comandaEntity.getStatus() != ComandaStatus.BLANK && //
                comandaEntity.getStatus() != ComandaStatus.CLOSED;
    }

    public ComandaEntity getComanda(final String sellerEmail, final String idComanda) {
        final SellerEntity sellerEntity = this.sellerRepository.findByEmail(sellerEmail);

        return this.comandaRepository.findBySellerAndIdComanda(sellerEntity, idComanda);
    }

    public void closingComanda(final String idComanda, final String sellerEmail, final String buyerEmail) throws
            Exception {
        final SellerEntity sellerEntity = this.sellerRepository.findByEmail(sellerEmail);
        final ComandaEntity comandaEntity = this.comandaRepository.findBySellerAndIdComanda(sellerEntity, idComanda);

        if (ComandaStatus.OPEN.equals(comandaEntity.getStatus())) {
            comandaEntity.setStatus(ComandaStatus.CLOSING);
            this.comandaRepository.save(comandaEntity);

            //Cria notificacao de fechamento para o vendedor
            this.notificationService.createClosingNotification(idComanda, sellerEmail, buyerEmail);
        } else {
            throw new Exception("Transicao de status invalida");
        }

    }

    public void closeToPayment(final String idComanda, final String sellerEmail, final BigDecimal value, final String
            image) throws Exception {
        final SellerEntity sellerEntity = this.sellerRepository.findByEmail(sellerEmail);
        final ComandaEntity comandaEntity = this.comandaRepository.findBySellerAndIdComanda(sellerEntity, idComanda);

        if (ComandaStatus.CLOSING.equals(comandaEntity.getStatus())) {
            final ImageEntity imageEntity = ImageEntity.builder().blob(image).creationDate(new Date()).build();
            this.imageRepository.save(imageEntity);

            comandaEntity.setStatus(ComandaStatus.PAYING);
            comandaEntity.setValue(value);
            comandaEntity.setImageFinal(imageEntity);
            this.comandaRepository.save(comandaEntity);

            //Cria notificacao de fechamento e solicitacao de pagamento para o comprador
            this.notificationService.createPayingNotification(idComanda, sellerEmail);
        } else {
            throw new Exception("Transicao de status invalida");
        }
    }

    public void processPaidComanda(final String idComanda, final String sellerEmail, final BigDecimal value, final
    String transactionCode, final String status) throws Exception {
        final SellerEntity sellerEntity = this.sellerRepository.findByEmail(sellerEmail);
        final ComandaEntity comandaEntity = this.comandaRepository.findBySellerAndIdComanda(sellerEntity, idComanda);

        if (ComandaStatus.PAYING.equals(comandaEntity.getStatus())) {

            final TransactionEntity transactionEntity = TransactionEntity.builder().comandaEntity(comandaEntity).code
                    (transactionCode).status(status).value(value).build();
            this.transactionRepository.save(transactionEntity);

            if ("OK".equals(status)) {
                comandaEntity.setStatus(ComandaStatus.PAID);
                this.comandaRepository.save(comandaEntity);

                //Cria notificacao de paga
                this.notificationService.createPaidNotification(idComanda, sellerEmail);
            } else {
                comandaEntity.setStatus(ComandaStatus.REFUSED);
                this.comandaRepository.save(comandaEntity);
                //Cria notificacao de recusada
                this.notificationService.createRefusedNotification(idComanda, sellerEmail);
            }

        } else {
            throw new Exception("Transicao de status invalida");
        }

    }

    public void finalizeComanda(final String idComanda, final String sellerEmail) throws Exception {
        final SellerEntity sellerEntity = this.sellerRepository.findByEmail(sellerEmail);
        final ComandaEntity comandaEntity = this.comandaRepository.findBySellerAndIdComanda(sellerEntity, idComanda);

        if (ComandaStatus.PAID.equals(comandaEntity.getStatus()) || ComandaStatus.REFUSED
                .equals(comandaEntity.getStatus())) {

            comandaEntity.setStatus(ComandaStatus.CLOSED);
            this.comandaRepository.save(comandaEntity);
        } else {
            throw new Exception("Transicao de status invalida");
        }
    }
}
