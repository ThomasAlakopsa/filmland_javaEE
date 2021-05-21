package nl.sogeti.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.Singleton;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

@Singleton
public class Hasher {

    private final static SecureRandom random = new SecureRandom();

    public static byte[] hashPassword(String plainPassword) {

        byte[] salt = new byte[16];
        random.nextBytes(salt);

        try{
        KeySpec spec = new PBEKeySpec(plainPassword.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        return factory.generateSecret(spec).getEncoded();

        }catch (Exception e){
            throw new RuntimeException("Er gaat iets mis");
        }

    }
}
