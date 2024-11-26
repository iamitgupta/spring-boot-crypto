package com.crypto.service;

import com.crypto.model.Key;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Service
@Slf4j
public class KeyService {

    public Key generateKey(String algorithm, int keySize) {
        try {
            if ("AES".equalsIgnoreCase(algorithm)) {
                // Generate AES key
                SecretKey aesKey = generateAESKey(keySize);
                String base64AESKey = getBase64AESKey(aesKey);
                return new Key(base64AESKey, null);
            } else {
                // Generate key pair for RSA, DSA, EC
                KeyPair keyPair = generateKeyPair(algorithm, keySize);
                String publicKey = getBase64PublicKey(keyPair.getPublic());
                String privateKey = getBase64PrivateKey(keyPair.getPrivate());
                return new Key(publicKey, privateKey);
            }
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
            log.error("Error generating key pair: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("IllegalArgumentException: " + e.getMessage());
        }
        return null;
    }

    // Method to generate key pair based on the algorithm
    public KeyPair generateKeyPair(String algorithm, int keySize) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);

        if ("RSA".equalsIgnoreCase(algorithm)) {
            keyPairGenerator.initialize(keySize);  // Initialize for RSA with key size
        } else if ("DSA".equalsIgnoreCase(algorithm)) {
            keyPairGenerator.initialize(keySize);  // Initialize for DSA with key size
        } else if ("EC".equalsIgnoreCase(algorithm)) {
            keyPairGenerator.initialize(keySize);  // Initialize for EC (Elliptic Curve)
        }

        return keyPairGenerator.generateKeyPair();  // Return generated key pair
    }

    public SecretKey generateAESKey(int keySize) throws NoSuchAlgorithmException {
        if (keySize != 128 && keySize != 192 && keySize != 256) {
            throw new IllegalArgumentException("AES key size must be 128, 192, or 256 bits.");
        }
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(keySize);
        return keyGenerator.generateKey();
    }

    // Method to get public key from Base64 encoded string
    public PublicKey getPublicKey(String base64PublicKey, String algorithm) throws Exception {
        byte[] decoded = Base64.getDecoder().decode(base64PublicKey);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
        return keyFactory.generatePublic(keySpec);
    }

    // Method to get private key from Base64 encoded string
    public PrivateKey getPrivateKey(String base64PrivateKey, String algorithm) throws Exception {
        byte[] decoded = Base64.getDecoder().decode(base64PrivateKey);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
        return keyFactory.generatePrivate(keySpec);
    }

    public SecretKeySpec getSecretKeySpec(String key, String algorithm) {
        return new SecretKeySpec(Base64.getDecoder().decode(key), algorithm);
    }

    // Convert PublicKey to Base64 encoded string
    public String getBase64PublicKey(PublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    // Convert PrivateKey to Base64 encoded string
    public String getBase64PrivateKey(PrivateKey privateKey) {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    // Convert AES SecretKey to Base64 encoded string
    public String getBase64AESKey(SecretKey secretKey) {
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }
}
