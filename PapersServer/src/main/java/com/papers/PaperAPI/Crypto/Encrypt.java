package com.papers.PaperAPI.Crypto;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64DecoderStream;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64EncoderStream;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
    private static SecretKey key;
    private static Cipher encipher;
    private static Cipher decipher;

    static {
        try {
            key = KeyGenerator.getInstance("DES").generateKey();
            encipher = Cipher.getInstance("DES");
            decipher = Cipher.getInstance("DES");
            encipher.init(Cipher.ENCRYPT_MODE, key);
            decipher.init(Cipher.DECRYPT_MODE, key);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String s) {
        try {
            byte[] utf8 = s.getBytes("UTF8");
            byte[] enc = encipher.doFinal(utf8);
            enc = BASE64EncoderStream.encode(enc);
            return new String(enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String s) {
        byte[] dec;
        byte[] utf8;
        try {
            dec = BASE64DecoderStream.decode(s.getBytes());
            utf8 = decipher.doFinal(dec);
            return new String(utf8, "UTF8");
        } catch (Exception e) {
        }
        return "";
    }
}
