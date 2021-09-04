package com.example.testwithoutspringcontext;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SomeRequest {
    private String name;
    private String foo;
    private String bar;

    public SomeEntity toEntity() {
        return new SomeEntity(null, name, foo, bar);
    }
}
