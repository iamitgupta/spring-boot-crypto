package com.crypto.constants;

public enum SigningAlgorithm {
    RSA_SHA256("SHA256withRSA"),
    RSA_SHA384("SHA384withRSA"),
    RSA_SHA512("SHA512withRSA"),
    DSA_SHA256("SHA256withDSA"),
    DSA_SHA384("SHA384withDSA"),
    DSA_SHA512("SHA512withDSA"),
    ECDSA_SHA256("SHA256withECDSA"),
    ECDSA_SHA384("SHA384withECDSA"),
    ECDSA_SHA512("SHA512withECDSA"),
    EDDSA("EdDSA"),
    HMAC_SHA256("HmacSHA256"),
    HMAC_SHA384("HmacSHA384"),
    HMAC_SHA512("HmacSHA512");

    private final String algorithm;

    SigningAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }
}
