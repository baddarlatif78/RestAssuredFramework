package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {

    public static RequestSpecification getRequestSpec() {

        return new RequestSpecBuilder()

                .setContentType("application/json")

                .setAccept("application/json")

                .build();
    }
}