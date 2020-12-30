package com.cubecube.rumirumi.login.account;

import com.cubecube.rumirumi.login.Encryptionable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Encrypter implements Encryptionable {
    @Override
    public String encrypt(String target) {
        String rs = "";
        try {
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(target.getBytes());
            byte byteData[] = sh.digest();
            StringBuffer sb = new StringBuffer();
            for(byte b : byteData) {
                sb.append(Integer.toString((b&0xff) + 0x100, 16).substring(1));
            }
            rs = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            rs = null;
        }
        return rs;
    }
}
