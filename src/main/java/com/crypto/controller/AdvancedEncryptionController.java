package com.crypto.controller;

import com.crypto.model.AdvancedDecryption;
import com.crypto.model.AdvancedEncryption;
import com.crypto.service.AdvancedEncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/adv/encryption")
public class AdvancedEncryptionController {

    @Autowired
    private AdvancedEncryptionService encryptionService;

    @PostMapping("/encrypt")
    public ResponseEntity<?> encrypt(@RequestBody AdvancedEncryption advancedEncryption) throws Exception {
        return ResponseEntity.ok(encryptionService.encrypt(advancedEncryption));
    }

    @PostMapping("/decrypt")
    public ResponseEntity<?> decrypt(@RequestBody AdvancedDecryption advancedDecryption) throws Exception {
        return ResponseEntity.ok(encryptionService.decrypt(advancedDecryption));
    }
}
