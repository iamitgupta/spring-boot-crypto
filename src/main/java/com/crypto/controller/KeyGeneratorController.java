package com.crypto.controller;

import com.crypto.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/keygen")
public class KeyGeneratorController {

    @Autowired
    private KeyService keyGeneratorService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateKey(@RequestParam String algorithm, @RequestParam int keySize) {
        return ResponseEntity.ok(keyGeneratorService.generateKey(algorithm, keySize));
    }
}
