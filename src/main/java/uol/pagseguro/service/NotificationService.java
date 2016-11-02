package uol.pagseguro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uol.pagseguro.entity.*;
import uol.pagseguro.repository.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by machadolucas on 01/11/16.
 */
@Service
public class NotificationService {

    @Autowired
    ClosingNotificationRepository closingNotificationRepository;
    @Autowired
    PayingNotificationRepository payingNotificationRepository;
    @Autowired
    PaidNotificationRepository paidNotificationRepository;
    @Autowired
    RefusedNotificationRepository refusedNotificationRepository;

    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ComandaRepository comandaRepository;

    public void createClosingNotification(final String idComanda, final String sellerEmail, final String buyerEmail) {
        final SellerEntity sellerEntity = this.sellerRepository.findByEmail(sellerEmail);
        final ComandaEntity comandaEntity = this.comandaRepository.findBySellerAndIdComanda(sellerEntity, idComanda);

        final ClosingNotificationEntity entity = ClosingNotificationEntity.builder().sellerEmail(sellerEmail).comanda
                (comandaEntity).build();

        this.closingNotificationRepository.save(entity);
    }

    public void createPayingNotification(final String idComanda, final String sellerEmail) {
        final SellerEntity sellerEntity = this.sellerRepository.findByEmail(sellerEmail);
        final ComandaEntity comandaEntity = this.comandaRepository.findBySellerAndIdComanda(sellerEntity, idComanda);

        final PayingNotificationEntity entity = PayingNotificationEntity.builder().sellerEmail(sellerEmail).comanda
                (comandaEntity).build();

        this.payingNotificationRepository.save(entity);
    }

    public void createPaidNotification(final String idComanda, final String sellerEmail) {
        final SellerEntity sellerEntity = this.sellerRepository.findByEmail(sellerEmail);
        final ComandaEntity comandaEntity = this.comandaRepository.findBySellerAndIdComanda(sellerEntity, idComanda);

        final PaidNotificationEntity entity = PaidNotificationEntity.builder().sellerEmail(sellerEmail).comanda
                (comandaEntity).build();

        this.paidNotificationRepository.save(entity);
    }

    public void createRefusedNotification(final String idComanda, final String sellerEmail) {
        final SellerEntity sellerEntity = this.sellerRepository.findByEmail(sellerEmail);
        final ComandaEntity comandaEntity = this.comandaRepository.findBySellerAndIdComanda(sellerEntity, idComanda);

        final RefusedNotificationEntity entity = RefusedNotificationEntity.builder().sellerEmail(sellerEmail).comanda
                (comandaEntity).build();

        this.refusedNotificationRepository.save(entity);
    }

    //=====//=====//=====//=====//=====//=====//=====//=====//=====//=====//=====//=====//=====

    public List<String> getClosingNotificationsIds(final String sellerEmail) {
        final List<ClosingNotificationEntity> list = this.closingNotificationRepository.findAllBySellerEmail
                (sellerEmail);

        return list.stream().map(entity -> entity.getComanda().getIdComanda()).collect(Collectors.toList());
    }

    public List<String> getPayingNotificationsIds(final String sellerEmail) {
        final List<PayingNotificationEntity> list = this.payingNotificationRepository.findAllBySellerEmail(sellerEmail);

        return list.stream().map(entity -> entity.getComanda().getIdComanda()).collect(Collectors.toList());
    }

    public List<String> getPaidNotificationsIds(final String sellerEmail) {
        final List<PaidNotificationEntity> list = this.paidNotificationRepository.findAllBySellerEmail(sellerEmail);

        return list.stream().map(entity -> entity.getComanda().getIdComanda()).collect(Collectors.toList());
    }

    public List<String> getRefusedNotificationsIds(final String sellerEmail) {
        final List<RefusedNotificationEntity> list = this.refusedNotificationRepository.findAllBySellerEmail
                (sellerEmail);

        return list.stream().map(entity -> entity.getComanda().getIdComanda()).collect(Collectors.toList());
    }


}
