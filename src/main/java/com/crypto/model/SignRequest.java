package com.crypto.model;

import com.crypto.constants.EncryptionAlgorithm;
import com.crypto.constants.SigningAlgorithm;

public record SignRequest(String plainText, SigningAlgorithm signingAlgorithm, String privateKey, EncryptionAlgorithm keyAlgorithm) {
}
