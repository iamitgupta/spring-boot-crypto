package com.crypto.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Key(String publicKey, String privateKey) {

}
