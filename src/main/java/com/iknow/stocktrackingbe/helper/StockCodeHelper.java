package com.iknow.stocktrackingbe.helper;

import java.security.SecureRandom;
public class StockCodeHelper {
    public static String generateRandomCode(int len, int randNumOrigin, int randNumBound) {
        SecureRandom random = new SecureRandom();
        return random.ints(randNumOrigin, randNumBound + 1)
                .filter(i -> Character.isAlphabetic(i) || Character.isDigit(i)).limit(len)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }
}
