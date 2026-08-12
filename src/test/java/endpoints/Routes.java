package endpoints;

public final class Routes {

    private Routes() {
    }

    // Authentication
    public static final String AUTH =
            "/auth";

    // Booking
    public static final String BOOKINGS =
            "/booking";

    public static final String BOOKING_BY_ID =
            "/booking/{bookingId}";
}


