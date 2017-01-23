package com.ecodex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by bennsandoval@gmail.com on 4/15/15.
 */
public class SHA1 {

    public String getHash(String message) throws NoSuchAlgorithmException {
        String hash = "";
        byte[] buffer = message.getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(buffer);
        byte[] digest = md.digest();
        for(byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) hash += "0";
            hash += Integer.toHexString(b);
        }
        return hash;
    }

}
