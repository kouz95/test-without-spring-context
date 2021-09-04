package com.example.testwithoutspringcontext;

import org.springframework.stereotype.Component;

@Component
public class BarVerifier {
    public void verifyBar(String bar) {
        if (bar.isBlank()) {
            throw new IllegalArgumentException();
        }
    }
}
