package com.crypto.service;

import com.crypto.model.AdvancedDecryption;
import com.crypto.model.AdvancedEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

@Service
public class AdvancedEncryptionService {

    @Autowired
    private KeyService keyService;

    public String encrypt(AdvancedEncryption advancedEncryption) throws Exception {
        PublicKey publicKey = keyService.getPublicKey(advancedEncryption.publicKey(), advancedEncryption.algorithm().name());
        Cipher cipher = Cipher.getInstance(advancedEncryption.algorithm().name());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(advancedEncryption.plainText().getBytes()));
    }

    public String decrypt(AdvancedDecryption advancedDecryption) throws Exception {
        PrivateKey privateKey = keyService.getPrivateKey(advancedDecryption.privateKey(), advancedDecryption.algorithm().name());
        Cipher cipher = Cipher.getInstance(advancedDecryption.algorithm().name());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(advancedDecryption.encryptedText())));
    }
}
