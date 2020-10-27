package test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class Encryptor {
    public static String encrypt(String key, String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            String base64EncodedKey = Base64.getEncoder().encodeToString(encrypted);
            return Base64.getEncoder().encodeToString(encrypted);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    public static void main (String[] args) {
    	
    	Encryptor e = new Encryptor();
    	String key = "Bar12345Bar12345"; // 128 bit key
        String initVector = "RandomInitVector"; // 16 bytes IV
        
        
    	String s = e.encrypt(key, initVector, "Hello World");
    	System.out.println(s);
    }
}

