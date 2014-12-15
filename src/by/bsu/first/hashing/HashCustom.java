package by.bsu.first.hashing;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCustom
{
    public static String getHashingCustom(String input) {

        String hashingString = null;
        if(null == input) return null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(), 0, input.length());
            hashingString = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashingString;
    }
}