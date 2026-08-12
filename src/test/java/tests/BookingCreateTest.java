package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import endpoints.BookingEndpoints;
import payloads.BookingPayloads;
import utils.TestDataUtils;
import utils.RetryAnalyzer;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BookingCreateTest extends BaseTest {

    @Test( priority = 2,
    	    dependsOnMethods = "createAuthToken",
    	    retryAnalyzer = RetryAnalyzer.class)
    public void createBooking() {

    	logger.info("========== CREATE BOOKING ==========");

        Response response =
                given()
                    .contentType("application/json")
                    .body(BookingPayloads.createBooking())

                .when()
                    .post(BookingEndpoints.BOOKINGS)

                .then()
                    .statusCode(200)

                    .body(
                        "booking.firstname",
                        equalTo("Baddar"))

                    .body(
                        "booking.lastname",
                        equalTo("Latif"))

                    .body(
                        "booking.totalprice",
                        equalTo(150))

                    .body(
                        "booking.depositpaid",
                        equalTo(true))

                    .extract()
                    .response();

        int bookingId =
                response.jsonPath()
                        .getInt("bookingid");

        Assert.assertTrue(
                bookingId > 0,
                "Booking ID was not generated");

        // Store booking ID
        TestDataUtils.setBookingId(bookingId);

        logger.info("Booking created successfully. Booking ID: {}",
                bookingId);
    }
}

