package com.example.testwithoutspringcontext;

import org.springframework.stereotype.Component;

@Component
public class FooVerifier {
    public void verifyFoo(String foo) {
        if (foo.isBlank()) {
            throw new IllegalArgumentException();
        }
    }
}
