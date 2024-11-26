package com.crypto.model;

import com.crypto.constants.EncryptionAlgorithm;
import com.crypto.constants.SigningAlgorithm;

public record VerifyRequest(String plainText, String signature, SigningAlgorithm signingAlgorithm, String publicKey, EncryptionAlgorithm keyAlgorithm) {
}
