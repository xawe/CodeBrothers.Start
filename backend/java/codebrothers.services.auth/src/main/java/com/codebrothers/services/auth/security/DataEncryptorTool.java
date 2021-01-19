package com.codebrothers.services.auth.security;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

public class DataEncryptorTool {
    private String encryption_algorithm = "DES";
    private String transformation = "DES/ECB/PKCSSPadding";
    private String privateCustomKey = "";
    private String publicCustomKey = "";
    private String secretKey = "";
    private boolean withPublicAndPrivateKey = false;

    public DataEncryptorTool(boolean withPublicAndPrivateKey) {
        this.withPublicAndPrivateKey = withPublicAndPrivateKey;

        if (this.withPublicAndPrivateKey) {
            this.encryption_algorithm = "RSA";
            this.transformation = "RSA/ECB/PKCS1Padding";
        }
    }

    public BigInteger encryptToNumber(byte[] data) {
        return new BigInteger(data);
    }

    public String decryptFromNumber(BigInteger number) {
        return new String(decrypt(number.toByteArray()));
    }
    
    public void generateSecretKey() throws NoSuchAlgorithmException {
        SecretKey secretKey = KeyGenerator.getInstance(this.encryption_algorithm).generateKey();

        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }
    
    public void generatePrivateAndPublicKey() throws NoSuchAlgorithmException {
        KeyPair keyPair = KeyPairGenerator.getInstance(this.encryption_algorithm).generateKeyPair();

        this.publicCustomKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        this.privateCustomKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
    }

    private SecretKey getSecretKey() throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] decoded = Base64.getMimeDecoder().decode(this.secretKey);

        SecretKeySpec secretKeySpec = new SecretKeySpec(decoded, this.encryption_algorithm);

        return SecretKeyFactory.getInstance(this.encryption_algorithm).generateSecret(secretKeySpec);
    }

    private PublicKey getPublicCustomKey() throws InvalidKeySpecException, NoSuchAlgorithmException {

        byte[] decoded = Base64.getMimeDecoder().decode(this.publicCustomKey);

        X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(decoded);

        return KeyFactory.getInstance(this.encryption_algorithm).generatePublic(encodedKeySpec);
    }

    private PrivateKey getPrivateCustomKey() throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] decoded = Base64.getMimeDecoder().decode(this.privateCustomKey);

        PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(decoded);

        return KeyFactory.getInstance(this.encryption_algorithm).generatePrivate(encodedKeySpec);
    }

    public byte[] encrypt(String text) {
        byte[] encryptedText = null;

        try {
            text = Optional.ofNullable(text).orElse("");

            Cipher cipher = Cipher.getInstance(this.transformation);

            if (this.withPublicAndPrivateKey) {
                cipher.init(Cipher.PUBLIC_KEY, this.getPublicCustomKey());
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, this.getSecretKey());
            }

            encryptedText = cipher.doFinal(text.getBytes());
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidKeySpecException
                | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        return encryptedText;
    }

    public byte[] decrypt(byte[] encryptedText) {
        byte[] decryptedText = null;
        
        try {
            Cipher cipher = Cipher.getInstance(this.transformation);

            if (this.withPublicAndPrivateKey) {
                cipher.init(Cipher.PRIVATE_KEY, this.getPrivateCustomKey());
            } else {
                cipher.init(Cipher.DECRYPT_MODE, this.getSecretKey());
            }

            decryptedText = cipher.doFinal(encryptedText);
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidKeySpecException
                | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        
        return decryptedText;
    }
}
