package payloads;

import payloads.BookingPayload.BookingDates;

public class BookingPayloads {

    public static BookingPayload createBooking() {

        BookingDates dates =
                new BookingDates(
                        "2026-08-10",
                        "2026-08-15");

        return new BookingPayload(
                "Baddar",
                "Latif",
                150,
                true,
                dates,
                "Breakfast");
    }

    public static BookingPayload updateBooking() {

        BookingDates dates =
                new BookingDates(
                        "2026-08-20",
                        "2026-08-25");

        return new BookingPayload(
                "Baddar Updated",
                "Latif Updated",
                200,
                false,
                dates,
                "Lunch");
    }
}


