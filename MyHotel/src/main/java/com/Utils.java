package com;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Utils {
    public static final String USAGE_ALGORITHM = "SHA-256";

    public static String hashString(String str) {
        String hashString = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(USAGE_ALGORITHM);
            byte[] hash = messageDigest.digest(str.getBytes(StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b: hash) {
                stringBuilder.append(String.format("%02x", b));
            }
            hashString = stringBuilder.toString();
        }
        catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return hashString;
    }
}
