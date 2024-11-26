package com.crypto.controller;

import com.crypto.model.HashRequest;
import com.crypto.service.EncryptionService;
import com.crypto.service.HashingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hash")
public class HashController {

    @Autowired
    private HashingService hashingService;

    @PostMapping("/generate")
    public ResponseEntity<?> encrypt(@RequestBody HashRequest hashRequest) throws Exception {
        return ResponseEntity.ok(hashingService.hash(hashRequest));
    }
}
