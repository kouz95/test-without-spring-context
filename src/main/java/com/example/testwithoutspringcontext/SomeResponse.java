package com.example.testwithoutspringcontext;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SomeResponse {
    private Long id;
    private String name;
    private String foo;
    private String bar;

    public static SomeResponse of(SomeEntity entity) {
        return new SomeResponse(entity.getId(), entity.getName(), entity.getFoo(), entity.getBar());
    }
}
