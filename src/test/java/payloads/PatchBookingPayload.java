package payloads;

public class PatchBookingPayload {

    private String firstname;

    public PatchBookingPayload() {
    }

    public PatchBookingPayload(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}



