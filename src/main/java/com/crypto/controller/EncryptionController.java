package com.crypto.controller;

import com.crypto.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/encryption")
public class EncryptionController {

    @Autowired
    private EncryptionService encryptionService;

    @PostMapping("/encrypt")
    public ResponseEntity<?> encrypt(@RequestParam String plainText, @RequestParam String key, @RequestParam String algorithm) throws Exception {
        return ResponseEntity.ok(encryptionService.encrypt(plainText, key, algorithm));
    }

    @PostMapping("/decrypt")
    public ResponseEntity<?> decrypt(@RequestParam String encryptedText, @RequestParam String key, @RequestParam String algorithm) throws Exception {
        return ResponseEntity.ok(encryptionService.decrypt(encryptedText, key, algorithm));
    }
}
