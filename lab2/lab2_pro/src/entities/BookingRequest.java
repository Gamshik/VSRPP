package entities;

import java.io.Serializable;

public class BookingRequest implements Serializable {
    private final Client client;
    private int places;
    private final String roomClass;
    private int days;
    private boolean confirmed;
    private boolean paid;

    public BookingRequest(Client client, int places, String roomClass, int days) {
        this.client = client;
        this.places = places;
        this.roomClass = roomClass;
        this.days = days;
    }

    public Client getClient() {
        return client;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getRoomClass() {
        return this.roomClass;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getDays() {
        return days;
    }
}
