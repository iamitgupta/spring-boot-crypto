package com.crypto.controller;

import com.crypto.model.SignRequest;
import com.crypto.model.VerifyRequest;
import com.crypto.service.SignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signature")
public class SignatureController {

    @Autowired
    private SignatureService signatureService;

    // Endpoint to sign data
    @PostMapping("/sign")
    public ResponseEntity<?> signData(@RequestBody SignRequest signRequest) throws Exception {
        return ResponseEntity.ok(signatureService.signData(signRequest));
    }

    // Endpoint to verify signature
    @PostMapping("/verify")
    public ResponseEntity<?> verifySignature(@RequestBody VerifyRequest verifyRequest) throws Exception {
        return ResponseEntity.ok(signatureService.verifySignature(verifyRequest));
    }
}
