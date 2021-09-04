package com.example.testwithoutspringcontext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class SomeTestWithoutMockMvc {
    @Mock
    private SomeRepository someRepository;

    @DisplayName("create Something")
    @TestFactory
    Stream<DynamicTest> create() {
        given(someRepository.save(any())).willReturn(new SomeEntity(1L, "name", "foo", "bar"));
        SomeController someController = new SomeController(new SomeService(someRepository), new FooVerifier(), new BarVerifier());

        return Stream.of(
                dynamicTest("happy case", () -> {
                    ResponseEntity<SomeResponse> response = someController.create(new SomeRequest("name", "foo", "bar"));

                    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
                    assertThat(response.getBody())
                            .usingRecursiveComparison()
                            .isEqualTo(new SomeResponse(1L, "name", "foo", "bar"));
                }),
                dynamicTest("empty foo", () ->
                        assertThatIllegalArgumentException().isThrownBy(() -> someController.create(new SomeRequest("name", "", "bar")))
                ),
                dynamicTest("empty bar", () ->
                        assertThatIllegalArgumentException().isThrownBy(() -> someController.create(new SomeRequest("name", "foo", "")))
                ),
                dynamicTest("empty name", () ->
                        assertThatIllegalArgumentException().isThrownBy(() -> someController.create(new SomeRequest("", "foo", "bat")))
                )
        );
    }
}
