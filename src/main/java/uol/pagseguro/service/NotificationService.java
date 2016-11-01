package uol.pagseguro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uol.pagseguro.repository.ClosingNotificationRepository;
import uol.pagseguro.repository.PaidNotificationRepository;
import uol.pagseguro.repository.PayingNotificationRepository;
import uol.pagseguro.repository.RefusedNotificationRepository;

import java.util.ArrayList;
import java.util.List;

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

    public void createClosingNotification(final String idComanda, final String sellerEmail, final String buyerEmail) {

    }

    public void createPayingNotification(final String idComanda, final String sellerEmail, final String buyerEmail) {

    }

    public void createPaidNotification(final String idComanda, final String sellerEmail, final String buyerEmail) {

    }

    public void createRefusedNotification(final String idComanda, final String sellerEmail, final String buyerEmail) {

    }

    public List<String> getClosingNotificationsIds(final String sellerEmail) {
        final List<String> idsComanda = new ArrayList<>();


        return idsComanda;
    }

    public List<String> getPayingNotificationsIds(final String sellerEmail) {
        final List<String> idsComanda = new ArrayList<>();
        return idsComanda;
    }

    public List<String> getPaidNotificationsIds(final String sellerEmail) {
        final List<String> idsComanda = new ArrayList<>();
        return idsComanda;
    }

    public List<String> getRefusedNotificationsIds(final String sellerEmail) {
        final List<String> idsComanda = new ArrayList<>();
        return idsComanda;
    }


}
