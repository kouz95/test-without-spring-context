package com.example.testwithoutspringcontext;

import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SomeTest {
    @Mock
    private SomeRepository someRepository;

    @InjectMocks
    private SomeService someService;

    @DisplayName("create Something")
    @Test
    void name() {
        // given
        given(someRepository.save(any())).willReturn(new SomeEntity(1L, "name", "foo", "bar"));

        RestAssuredMockMvc.given()
                .standaloneSetup(MockMvcBuilders.standaloneSetup(new SomeController(someService)))
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
                .body("bar", equalTo("bar"));
    }
}