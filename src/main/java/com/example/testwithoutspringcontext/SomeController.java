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

    @PostMapping("/some")
    public ResponseEntity<SomeResponse> create(@RequestBody SomeRequest request) {
        SomeResponse response = someService.create(request);
        return ResponseEntity.ok(response);
    }
}
