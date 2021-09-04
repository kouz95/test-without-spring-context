package com.example.testwithoutspringcontext;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.stream.Stream;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SomeTest {
    @Mock
    private SomeRepository someRepository;

    @DisplayName("create Something")
    @TestFactory
    Stream<DynamicTest> create() {
        given(someRepository.save(any())).willReturn(new SomeEntity(1L, "name", "foo", "bar"));
        SomeController someController = new SomeController(new SomeService(someRepository), new FooVerifier(), new BarVerifier());

        return Stream.of(
                dynamicTest("happy case", () ->
                        given()
                                .standaloneSetup(MockMvcBuilders.standaloneSetup(someController)
                                        .setControllerAdvice(new SomeControllerAdvice()))
                                .contentType(ContentType.JSON)
                                .body(new SomeRequest("name", "foo", "bar"))
                                .accept(ContentType.JSON)
                                .when()
                                .post("/some")
                                .then()
                                .assertThat()
                                .statusCode(200)
                                .body("id", equalTo(1))
                                .body("name", equalTo("name"))
                                .body("foo", equalTo("foo"))
                                .body("bar", equalTo("bar"))),
                dynamicTest("empty foo", () ->
                        given()
                                .standaloneSetup(MockMvcBuilders.standaloneSetup(someController)
                                        .setControllerAdvice(new SomeControllerAdvice()))
                                .contentType(ContentType.JSON)
                                .body(new SomeRequest("name", "", "bar"))
                                .accept(ContentType.JSON)
                                .when()
                                .post("/some")
                                .then()
                                .assertThat()
                                .statusCode(400)),
                dynamicTest("empty bar", () ->
                        given()
                                .standaloneSetup(MockMvcBuilders.standaloneSetup(someController)
                                        .setControllerAdvice(new SomeControllerAdvice()))
                                .contentType(ContentType.JSON)
                                .body(new SomeRequest("name", "foo", ""))
                                .accept(ContentType.JSON)
                                .when()
                                .post("/some")
                                .then()
                                .assertThat()
                                .statusCode(400)),
                dynamicTest("empty name", () ->
                        given()
                                .standaloneSetup(MockMvcBuilders.standaloneSetup(someController)
                                        .setControllerAdvice(new SomeControllerAdvice()))
                                .contentType(ContentType.JSON)
                                .body(new SomeRequest("", "foo", "bar"))
                                .accept(ContentType.JSON)
                                .when()
                                .post("/some")
                                .then()
                                .assertThat()
                                .statusCode(400))
        );
    }
}