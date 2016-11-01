package uol.pagseguro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uol.pagseguro.vo.NotificationRequest;
import uol.pagseguro.vo.NotificationResponse;

/**
 * Created by machadolucas on 01/11/16.
 */
@RestController
public class NotificationController {

    /**
     * Cria uma notificacao
     *
     * @param notificationRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/notify")
    public ResponseEntity<NotificationResponse> createNotification(final NotificationRequest notificationRequest) {

        switch (notificationRequest.getStatus()){
            case CLOSING:{
                 break;
            }
            case PAYING:{
                break;
            }
            case PAID:{
                break;
            }
            case REFUSED:{
                break;
            }
                default:{

                }

        }
        return null;
    }


}
