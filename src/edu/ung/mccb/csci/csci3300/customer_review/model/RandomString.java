package edu.ung.mccb.csci.csci3300.customer_review.model;

import java.security.SecureRandom;
import java.util.Objects;

public class RandomString {
    /**
     * Generate a random string.
     */
    public String nextString(int length) {
        SecureRandom random = new SecureRandom();
        for (int index = 0; index < length; index++)
            outputBuffer[index] = charArray[random.nextInt(charArray.length)];

        return outputBuffer.toString();
    }

    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lower = upper.toLowerCase();
    private static final String digits = "0123456789";
    private static final String alphanum = upper + lower + digits;
    private static final char[] charArray = alphanum.toCharArray();
    private char[] outputBuffer;
}
