package org.example.registration_with_check;

import org.example.registration_with_check.model.UserEntity;
import org.example.registration_with_check.repository.UserRepository;
import org.example.registration_with_check.service.RegistrationService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest()
public class RegistrationServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    RegistrationService registrationService;

    @Test
    public void getAllUsersTest(){
        UserEntity entity = new UserEntity();
        entity.setLogin("login");
        entity.setFio("fio");
        entity.setPass("pass");
        entity.setMessageId("123");
        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(entity));
        Assert.assertEquals(registrationService.getAllUsers().get(0), entity.toString());
    }
}
