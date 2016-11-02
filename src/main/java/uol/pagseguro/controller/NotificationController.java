package uol.pagseguro.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uol.pagseguro.service.NotificationService;
import uol.pagseguro.vo.GetNotificationsRequest;
import uol.pagseguro.vo.GetNotificationsResponse;
import uol.pagseguro.vo.NotificationRequest;
import uol.pagseguro.vo.NotificationResponse;

import java.util.List;

/**
 * Created by machadolucas on 01/11/16.
 */
@Api
@RestController
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    /**
     * Cria uma notificacao
     *
     * @param notificationRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/notify")
    public ResponseEntity<NotificationResponse> createNotification(@RequestBody final NotificationRequest
                                                                               notificationRequest) {

        final NotificationResponse notificationResponse;

        switch (notificationRequest.getStatus()) {
            case CLOSING: {
                this.notificationService.createClosingNotification(notificationRequest.getIdComanda(),
                        notificationRequest.getSellerEmail(), notificationRequest.getBuyerEmail());
                notificationResponse = NotificationResponse.builder().build();
                break;
            }
            case PAYING: {
                this.notificationService.createPayingNotification(notificationRequest.getIdComanda(),
                        notificationRequest.getSellerEmail());
                notificationResponse = NotificationResponse.builder().build();
                break;
            }
            case PAID: {
                this.notificationService.createPaidNotification(notificationRequest.getIdComanda(),
                        notificationRequest.getSellerEmail());
                notificationResponse = NotificationResponse.builder().build();
                break;
            }
            case REFUSED: {
                this.notificationService.createRefusedNotification(notificationRequest.getIdComanda(),
                        notificationRequest.getSellerEmail());
                notificationResponse = NotificationResponse.builder().build();
                break;
            }
            default: {
                notificationResponse = NotificationResponse.builder().message("status invalido").build();
                return new ResponseEntity<>(notificationResponse, HttpStatus.BAD_REQUEST);
            }

        }
        return new ResponseEntity<>(notificationResponse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/notifications")
    public ResponseEntity<GetNotificationsResponse> getNotifications(@RequestBody final GetNotificationsRequest
                                                                                 getNotificationsRequest) {

        final GetNotificationsResponse getNotificationsResponse;
        switch (getNotificationsRequest.getStatus()) {
            case CLOSING: {
                final List<String> ids = this.notificationService.getClosingNotificationsIds(getNotificationsRequest
                        .getSellerEmail());
                getNotificationsResponse = GetNotificationsResponse.builder().count(ids.size()).idComandas(ids).build();
                break;
            }
            case PAYING: {
                final List<String> ids = this.notificationService.getPayingNotificationsIds(getNotificationsRequest
                        .getSellerEmail());
                getNotificationsResponse = GetNotificationsResponse.builder().count(ids.size()).idComandas(ids).build();
                break;
            }
            case PAID: {
                final List<String> ids = this.notificationService.getPaidNotificationsIds(getNotificationsRequest
                        .getSellerEmail());
                getNotificationsResponse = GetNotificationsResponse.builder().count(ids.size()).idComandas(ids).build();
                break;
            }
            case REFUSED: {
                final List<String> ids = this.notificationService.getRefusedNotificationsIds(getNotificationsRequest
                        .getSellerEmail());
                getNotificationsResponse = GetNotificationsResponse.builder().count(ids.size()).idComandas(ids).build();
                break;
            }
            default: {
                getNotificationsResponse = GetNotificationsResponse.builder().build();
                return new ResponseEntity<>(getNotificationsResponse, HttpStatus.BAD_REQUEST);
            }

        }
        return new ResponseEntity<>(getNotificationsResponse, HttpStatus.OK);

    }


}
