package com.example.testwithoutspringcontext;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SomeController {
    private final SomeService someService;
    private final FooVerifier fooVerifier;
    private final BarVerifier barVerifier;

    @PostMapping("/some")
    public ResponseEntity<SomeResponse> create(@RequestBody SomeRequest request) {
        dizzyLogic(request);
        fooVerifier.verifyFoo(request.getFoo());
        barVerifier.verifyBar(request.getBar());
        SomeResponse response = someService.create(request);
        return ResponseEntity.ok(response);
    }

    private void dizzyLogic(SomeRequest request) {
        if (request.getName().isBlank()) {
            throw new IllegalArgumentException();
        }
    }
}
