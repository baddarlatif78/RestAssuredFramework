package endpoints;

public class BookingEndpoints {

    private BookingEndpoints() {
        // Prevent object creation
    }

    public static final String BOOKINGS =
            "/booking";

    public static final String BOOKING_BY_ID =
            "/booking/{bookingId}";
}

