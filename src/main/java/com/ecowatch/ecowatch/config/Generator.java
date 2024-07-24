package com.ecowatch.ecowatch.config;

import java.security.SecureRandom;

public class Generator {
    private static final String OTP = "0123456789";

    private static final SecureRandom random = new SecureRandom();

    public static String generate() {
        int length = 5;
        StringBuilder stringBuilder = new StringBuilder(length);
        for(int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(OTP.length());
            stringBuilder.append(OTP.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }
}
