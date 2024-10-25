package entities;
import java.util.UUID;
import java.io.Serializable;

public class BookingRequest implements Serializable {
    private String id;
    private final Client client;
    private int places;
    private final String roomClass;
    private int days;
    private boolean confirmed;
    private boolean paid;

    public BookingRequest(Client client, int places, String roomClass, int days) {
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
        this.client = client;
        this.places = places;
        this.roomClass = roomClass;
        this.days = days;
    }

    public BookingRequest(String id, Client client, int places, String roomClass, int days) {
        this.id = id;
        this.client = client;
        this.places = places;
        this.roomClass = roomClass;
        this.days = days;
    }

    public Client getClient() {
        return client;
    }

    public int getPlaces() {
        return places;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getRoomClass() {
        return this.roomClass;
    }

    public String getId() {
        return id;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isPaid() {
        return paid;
    }

    public int getDays() {
        return days;
    }

    public void makePayment() {
        if (isConfirmed()) {
            System.out.println(client.getName() + " is making payment for the booking.");
            setPaid(true);
        } else {
            System.out.println("Cannot make payment. The booking is not confirmed.");
        }
    }
}
