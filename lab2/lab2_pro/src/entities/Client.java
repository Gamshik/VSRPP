package entities;

import java.io.Serializable;

public class Client implements Serializable {
    private String name;
    private String frfer;
    private transient String password;

    public Client(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public BookingRequest createBookingRequest(int places, String roomClass, int days) {
        return new BookingRequest(this, places, roomClass, days);
    }

    public void makePayment(BookingRequest request) {
        if (request.isConfirmed()) {
            System.out.println(name + " is making payment for the booking.");
            request.setPaid(true);
        } else {
            System.out.println("Cannot make payment. The booking is not confirmed.");
        }
    }
}
