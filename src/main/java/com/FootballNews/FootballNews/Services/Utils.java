package com.FootballNews.FootballNews.Services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Service
public class Utils {

        private final Random RANDOM = new SecureRandom();
        private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";


        public String generateStringId(int length) {
            StringBuilder returnValue = new StringBuilder(length);

            for (int i = 0; i < length; i++) {
                returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
            }

            return new String(returnValue);
        }



}
