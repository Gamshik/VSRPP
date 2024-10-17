import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        TransportService transportService = new TransportService();

        transportService.addRoute(
                "Route 1: A -> B",
                LocalDateTime.of(2024, 10, 18, 8, 0),
                LocalDateTime.of(2024, 10, 18, 9, 0),
                0.5
        );

        transportService.addRoute(
                "Route 2: B -> C",
                LocalDateTime.of(2024, 10, 18, 9, 30),
                LocalDateTime.of(2024, 10, 18, 10, 30),
                2.5
        );

        transportService.addRoute(
                "Route 3: C -> D",
                LocalDateTime.of(2024, 10, 18, 11, 0),
                LocalDateTime.of(2024, 10, 18, 12, 0),
                0.85
        );

        transportService.displayAllRoutes();
    }
}