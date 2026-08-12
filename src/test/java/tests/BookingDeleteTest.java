package tests;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import endpoints.BookingEndpoints;
import utils.LogUtils;
import utils.TestDataUtils;

import static io.restassured.RestAssured.given;

public class BookingDeleteTest extends BaseTest {

    private static final Logger logger =
            LogUtils.getLogger(BookingDeleteTest.class);

    @Test(
        priority = 7,
        dependsOnMethods = {
            "tests.BookingPatchTest.patchBooking"
        }
    )
    public void deleteBooking() {

        logger.info("========== DELETE BOOKING ==========");

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

        logger.info(
                "Deleting booking ID: {}",
                bookingId);

        given()
            .cookie("token", token)
            .pathParam("bookingId", bookingId)

        .when()
            .delete(BookingEndpoints.BOOKING_BY_ID)

        .then()
            .statusCode(201);

        logger.info(
                "Booking {} deleted successfully",
                bookingId);
    }
}
