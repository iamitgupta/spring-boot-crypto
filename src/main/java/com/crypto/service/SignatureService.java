package com.crypto.service;

import com.crypto.constants.SigningAlgorithm;
import com.crypto.model.SignRequest;
import com.crypto.model.VerifyRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

@Service
public class SignatureService {

    @Autowired
    private KeyService keyService;

    public String signData(SignRequest signRequest) throws Exception {
        // Decode private key from Base64
        PrivateKey privateKeyDecoded = keyService.getPrivateKey(signRequest.privateKey(), signRequest.keyAlgorithm().name());

        // Initialize the signature instance with the private key
        Signature signature = Signature.getInstance(signRequest.signingAlgorithm().getAlgorithm());
        signature.initSign(privateKeyDecoded);

        // Sign the data
        signature.update(signRequest.plainText().getBytes());
        byte[] signedData = signature.sign();

        // Return the Base64 encoded signature
        return Base64.getEncoder().encodeToString(signedData);
    }

    public boolean verifySignature(VerifyRequest verifyRequest) throws Exception {
        // Decode public key from Base64
        PublicKey publicKeyDecoded = keyService.getPublicKey(verifyRequest.publicKey(), verifyRequest.keyAlgorithm().name());

        // Initialize the signature instance with the public key
        Signature sig = Signature.getInstance(verifyRequest.signingAlgorithm().getAlgorithm());
        sig.initVerify(publicKeyDecoded);

        // Verify the signature
        sig.update(verifyRequest.plainText().getBytes());

        // Decode the Base64 signature and verify
        byte[] decodedSignature = Base64.getDecoder().decode(verifyRequest.signature());
        return sig.verify(decodedSignature);
    }
}
