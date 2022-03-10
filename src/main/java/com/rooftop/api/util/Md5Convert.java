package com.rooftop.api.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Convert {

    public static String getMd5(String text, int chars) throws NoSuchAlgorithmException {
        try {
            text = text + chars;
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digestText = md5.digest(text.getBytes());
            BigInteger outMd5Text = new BigInteger(1, digestText);
            String outMd5String = outMd5Text.toString(16);
            while (outMd5String.length() < 32) {
                outMd5String = "0" + outMd5String;
            }
            return outMd5String;

        } catch (Exception e) {
            return null;
        }

    }
}
