package entities;

import java.util.UUID;
import java.io.Serializable;

public class Client implements Serializable {
    private String id;
    private String name;

    public Client(String name) {
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
        this.name = name;
    }
    public Client(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public BookingRequest createBookingRequest(int places, String roomClass, int days) {
        return new BookingRequest(this, places, roomClass, days);
    }
}
