package com.crypto.service;

import com.crypto.constants.HashingAlgorithm;
import com.crypto.model.HashRequest;
import com.crypto.util.CryptoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
public class HashingService {

    public String hash(HashRequest hashRequest) {
        try {
            MessageDigest digest = MessageDigest.getInstance(hashRequest.algorithm().getAlgorithmName());
            byte[] hashBytes = digest.digest(hashRequest.plainText().getBytes(StandardCharsets.UTF_8));
            return CryptoUtils.bytesToHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            log.error("Error hashing input", e);
            return null;
        }
    }

}
