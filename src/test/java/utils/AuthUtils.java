package utils;

import org.apache.logging.log4j.Logger;

import endpoints.AuthEndpoints;
import payloads.AuthPayload;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthUtils {

    private static final Logger logger =
            LogUtils.getLogger(AuthUtils.class);

    private AuthUtils() {
    }

    public static String generateToken() {

        logger.info("Generating authentication token");

        Response response =
                given()
                    .contentType("application/json")
                    .body(AuthPayload.createAuthPayload())

                .when()
                    .post(AuthEndpoints.CREATE_TOKEN)

                .then()
                    .statusCode(200)
                    .extract()
                    .response();

        String token =
                response.jsonPath().getString("token");

        logger.info("Authentication token generated successfully");

        return token;
    }
}