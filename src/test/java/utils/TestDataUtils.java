package utils;

public class TestDataUtils {

    private static String token;
    private static int bookingId;

    // =====================================================
    // TOKEN
    // =====================================================

    public static void setToken(String authToken) {
        token = authToken;
    }

    public static String getToken() {
        return token;
    }

    // =====================================================
    // BOOKING ID
    // =====================================================

    public static void setBookingId(int id) {
        bookingId = id;
    }

    public static int getBookingId() {
        return bookingId;
    }

    // =====================================================
    // VALIDATION
    // =====================================================

    public static boolean isTokenAvailable() {

        return token != null && !token.isEmpty();
    }

    public static boolean isBookingIdAvailable() {

        return bookingId > 0;
    }
}

