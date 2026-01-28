package com.abfinance.api.client.security;

public interface SignatureGenerator {
    String auth(String payload);
}
