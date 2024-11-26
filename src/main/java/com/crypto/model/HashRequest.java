package com.crypto.model;

import com.crypto.constants.HashingAlgorithm;

public record HashRequest(String plainText, HashingAlgorithm algorithm) {
}
