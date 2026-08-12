package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import endpoints.BookingEndpoints;
import payloads.BookingPayloads;
import utils.TestDataUtils;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BookingUpdateTest extends BaseTest {

    @Test(
        priority = 5,
        dependsOnMethods = "tests.BookingCreateTest.createBooking"
    )
    public void updateBooking() {

        System.out.println(
                "========== PUT UPDATE BOOKING ==========");

        int bookingId =
                TestDataUtils.getBookingId();

        String token =
                TestDataUtils.getToken();

        Assert.assertTrue(
                bookingId > 0,
                "Booking ID is not available");

        Assert.assertNotNull(
                token,
                "Authentication token is not available");

        given()
            .contentType("application/json")
            .accept("application/json")

            .cookie(
                "token",
                token)

            .pathParam(
                "bookingId",
                bookingId)

            .body(
                BookingPayloads.updateBooking())

        .when()
            .put(
                BookingEndpoints.BOOKING_BY_ID)

        .then()
            .statusCode(200)

            .body(
                "firstname",
                equalTo("Baddar Updated"))

            .body(
                "lastname",
                equalTo("Latif Updated"))

            .body(
                "totalprice",
                equalTo(200))

            .body(
                "depositpaid",
                equalTo(false))

            .body(
                "additionalneeds",
                equalTo("Lunch"))

            .log()
            .all();
    }
}

