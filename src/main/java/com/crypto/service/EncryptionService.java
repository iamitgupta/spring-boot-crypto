package com.crypto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class EncryptionService {

    @Autowired
    private KeyService keyService;

    public String encrypt(String plainText, String key, String algorithm) throws Exception {
        SecretKeySpec secretKeySpec = keyService.getSecretKeySpec(key, algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String encryptedText, String key, String algorithm) throws Exception {
        SecretKeySpec secretKeySpec = keyService.getSecretKeySpec(key, algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decrypted = Base64.getDecoder().decode(encryptedText);
        return new String(cipher.doFinal(decrypted), StandardCharsets.UTF_8);
    }
}
