package org.example.registration_with_check.service;

import org.example.registration_with_check.model.User;
import org.example.registration_with_check.model.UserEntity;
import org.example.registration_with_check.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final MessagingServiceStub messagingServiceStub;

    @Autowired
    public RegistrationService(UserRepository userRepository, MessagingServiceStub messagingServiceStub){
        this.userRepository = userRepository;
        this.messagingServiceStub = messagingServiceStub;
    }

    /**
     * Синхронное сохранение данных присланных пользователем из формы и отправка этих данных на валидацию.
     * Пользователь сразу получает сообщение об ошибке в случае исключений базы или при отправке на валидацию.
     *
     * @param user
     * @throws NoSuchAlgorithmException
     */
    public void registration(User user) throws NoSuchAlgorithmException {
        UserEntity entity = new UserEntity(user.getLogin(), Utility.sha256HexString(user.getPass()),
                user.getMail(), user.getFio());
        userRepository.save(entity);
        String messageId = messagingServiceStub.send(entity.toString());
        entity.setMessageId(messageId);
        userRepository.save(entity);
    }

    /**
     * Получение всех пользователей из базы
     */
    public List<String> getAllUsers(){
        List<String> strings = new ArrayList<>();
        for (UserEntity user : userRepository.findAll()) {
            strings.add(user.toString());
        }
        return strings;
    }
}
