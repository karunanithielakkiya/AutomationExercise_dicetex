package org.learn.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.equalTo;

public class ResponseSpecs {

    public static ResponseSpecification validResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification notFoundResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .expectContentType(ContentType.TEXT)
                .expectBody(equalTo("Not Found"))
                .build();
    }

    public static ResponseSpecification internalServerErrorSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(500)
                .expectContentType(ContentType.TEXT)
                .expectBody( equalTo("Internal Server Error"))
                .build();
    }
}
