package org.example.registration_with_check;

import org.example.registration_with_check.service.Utility;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.security.NoSuchAlgorithmException;

public class UtilityTest {

    @Test
    public void sha256HexStringTest() throws NoSuchAlgorithmException {
        String string = "abc";
        String sha256StringCheck = "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad";
        assert(Utility.sha256HexString(string).equals(sha256StringCheck));
    }

    @Test
    public void shouldSleepTest(){
        Assert.isInstanceOf(Boolean.class, Utility.shouldSleep());
    }

    @Test
    public void shouldThrowTimeoutTest(){
        Assert.isInstanceOf(Boolean.class, Utility.shouldThrowTimeout());
    }
}
