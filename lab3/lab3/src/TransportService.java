import java.time.LocalDateTime;
import java.util.ArrayList;

public class TransportService {
    private final ArrayList<RouteInfo> routes = new ArrayList<>();

    public void addRoute(String routeLine, LocalDateTime departureTime, LocalDateTime arrivalTime, double cost) {
        RouteInfo newRoute = new RouteInfo(routeLine, departureTime, arrivalTime, cost);
        routes.add(newRoute);
    }

    public void displayAllRoutes() {
        if (routes.isEmpty()) {
            System.out.println("Route list is empty.");
        } else {
            System.out.println("Route list:");
            for (RouteInfo route : routes) {
                route.displayInfo();
                System.out.println("-----------------------");
            }
        }
    }

    class RouteInfo {
        private final String routeLine;
        private final LocalDateTime departureTime;
        private final LocalDateTime arrivalTime;
        private final double cost;

        public RouteInfo(String routeLine, LocalDateTime departureTime, LocalDateTime arrivalTime, double cost) {
            this.routeLine = routeLine;
            this.departureTime = departureTime;
            this.arrivalTime = arrivalTime;
            this.cost = cost;
        }

        public void displayInfo() {
            System.out.println("Line: " + routeLine);
            System.out.println("Departure time: " + departureTime);
            System.out.println("Arrival time: " + arrivalTime);
            System.out.println("Cost: " + cost + " Byn.");
        }
    }
}
