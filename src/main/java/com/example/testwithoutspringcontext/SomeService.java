package com.example.testwithoutspringcontext;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SomeService {
    private final SomeRepository someRepository;

    public SomeResponse create(SomeRequest request) {
        SomeEntity entity = request.toEntity();
        SomeEntity saved = someRepository.save(entity);
        return SomeResponse.of(saved);
    }
}
