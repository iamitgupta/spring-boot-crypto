package com.crypto.model;

import com.crypto.constants.EncryptionAlgorithm;

public record AdvancedDecryption(String encryptedText, String privateKey, EncryptionAlgorithm algorithm) {
}
