package net.yeoman.nmpcaport.shared.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Utils {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqurtuvwxyz";


    public String generateRandomID(){

        return generateRandomString(30);
    }

    public String generateRandomString(int length){

        StringBuilder returnValue = new StringBuilder();

        for(int i = 0; i < length; i++){

            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }
}
