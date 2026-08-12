package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import endpoints.BookingEndpoints;
import utils.RequestSpec;
import utils.TestDataUtils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BookingGetTest extends BaseTest {

    @Test(priority = 3)
    public void getAllBookings() {

        logger.info("========== GET ALL BOOKINGS ==========");

        given()
            .spec(RequestSpec.getRequestSpec())

        .when()
            .get(BookingEndpoints.BOOKINGS)

        .then()
            .statusCode(200)
            .body("$", not(empty()))
            .log()
            .all();

        logger.info("GET ALL BOOKINGS PASSED");
    }


    @Test(
        priority = 4,
        dependsOnMethods =
            "tests.BookingCreateTest.createBooking"
    )
    public void getBookingById() {

        logger.info("========== GET BOOKING BY ID ==========");

        int bookingId =
                TestDataUtils.getBookingId();

        Assert.assertTrue(
                bookingId > 0,
                "Booking ID is not available");

        logger.info(
                "Retrieving booking ID: {}",
                bookingId);

        Response response =
                given()
                    .spec(RequestSpec.getRequestSpec())
                    .pathParam(
                        "bookingId",
                        bookingId)

                .when()
                    .get(
                        BookingEndpoints.BOOKING_BY_ID)

                .then()
                    .statusCode(200)

                    .body(
                        "firstname",
                        equalTo("Baddar"))

                    .body(
                        "lastname",
                        equalTo("Latif"))

                    .extract()
                    .response();

        logger.info(
                "Booking {} retrieved successfully",
                bookingId);

        logger.debug(
                "Response: {}",
                response.asPrettyString());
    }
}
