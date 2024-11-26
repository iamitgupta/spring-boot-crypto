package com.crypto.model;

import com.crypto.constants.EncryptionAlgorithm;

public record AdvancedEncryption(String plainText, String publicKey, EncryptionAlgorithm algorithm) {
}
