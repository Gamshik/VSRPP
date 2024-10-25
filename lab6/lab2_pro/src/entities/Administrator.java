package entities;

import java.util.ArrayList;
import java.util.List;

public class Administrator {
    private String name;

    public Administrator(String name) {
        this.name = name;
    }
    
    public ArrayList<BookingRequest> processRequests(List<BookingRequest> receivedRequests) {
        for (BookingRequest request : receivedRequests) {
            if (request.getRoomClass().equalsIgnoreCase("lux") && request.getDays() > 2) {
                request.setConfirmed(true);
                System.out.println("Request for " + request.getClient().getName() + " confirmed for room class "
                        + request.getRoomClass() + " by admin - " + this.name + " on days: " + request.getDays());
            } else {
                request.setConfirmed(false);
                System.out.println("Request for " + request.getClient().getName() + " declined for room class "
                        + request.getRoomClass() + " by admin - " + this.name + " on days: " + request.getDays());
            }
        }
        var processedRequests = new ArrayList<BookingRequest>(receivedRequests);
        receivedRequests.clear();
        return processedRequests;
    }
}
