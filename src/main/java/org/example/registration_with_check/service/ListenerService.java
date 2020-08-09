package org.example.registration_with_check.service;

import org.example.registration_with_check.model.UserEntity;
import org.example.registration_with_check.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Класс представляющий собой абстракцию брокера сообщений с обработкой посланных данных
 * пользователей в асинхронном режиме
 */
@Service
public class ListenerService {

    private static final Logger log = LoggerFactory.getLogger(ListenerService.class);

    private final UserRepository userRepository;
    private final MessagingService messagingService;
    private final SendMailer sendMailer;

    @Autowired
    public ListenerService(UserRepository userRepository, MessagingService messagingService,
                           SendMailer sendMailer) {
        this.userRepository = userRepository;
        this.messagingService = messagingService;
        this.sendMailer = sendMailer;
    }

    /**
     * Метод представляет собой абстракцию листенера слушающего очередь.
     *
     */
    @Scheduled(fixedRate=1000)
    public void checkRecords() {
        List<UserEntity> userEntities = userRepository.getAllUnprocessedUser();
        for (UserEntity userEntity : userEntities){
            boolean userValid = false;
            try {
                userValid = messagingService.receive(userEntity.getMessageId());
                userRepository.userIsProcessed(userEntity.getId());
            } catch (TimeoutException e) {
                log.error(e.toString());
            }
            if (userValid){
                try {
                    sendMailer.sendMail(userEntity.getMail(), "");
                    userRepository.mailIsSended(userEntity.getId());
                } catch (TimeoutException e) {
                    log.error(e.toString());
                }
            }
        }
    }
}
