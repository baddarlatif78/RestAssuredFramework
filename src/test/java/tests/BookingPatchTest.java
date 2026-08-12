package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import endpoints.BookingEndpoints;
import payloads.PatchBookingPayload;
import utils.TestDataUtils;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BookingPatchTest extends BaseTest {

	@Test(priority = 6, dependsOnMethods = "tests.BookingUpdateTest.updateBooking")
	public void patchBooking() {

		System.out.println("========== PATCH BOOKING ==========");

		int bookingId = TestDataUtils.getBookingId();

		String token = TestDataUtils.getToken();

		Assert.assertTrue(bookingId > 0, "Booking ID is not available");

		Assert.assertNotNull(token, "Authentication token is not available");

		
		PatchBookingPayload payload = new PatchBookingPayload();

		payload.setFirstname("Baddar PATCH");

		given().contentType("application/json").accept("application/json")

				.cookie("token", token)

				.pathParam("bookingId", bookingId)

				.body(payload)

				.when().patch(BookingEndpoints.BOOKING_BY_ID)

				.then().statusCode(200)

				.body("firstname", equalTo("Baddar PATCH"))

				.log().all();
	}
}
