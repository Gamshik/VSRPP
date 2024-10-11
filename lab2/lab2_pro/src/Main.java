import entities.Administrator;
import entities.BookingRequest;
import entities.Client;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        var admin = new Administrator("Gleb");

        var firstClient = new Client("Alice", "password123");
        var secondClient = new Client("Bob", "securepass");

        var firstBookingRequest = firstClient.createBookingRequest(3, "lux", 5);
        var secondBookingRequest = secondClient.createBookingRequest(2, "standard", 3);

        try {
            var fileOutputStreamForBookingRequest = new FileOutputStream("booking_requests.ser");

            var bookingRequests = new ArrayList<BookingRequest>();
            bookingRequests.add(firstBookingRequest);
            bookingRequests.add(secondBookingRequest);

            writeObjectsToFile(fileOutputStreamForBookingRequest, bookingRequests);

            fileOutputStreamForBookingRequest.close();

            var savedBookingRequests = getBookingRequests();

            System.out.println("Deserialized Requests:");
            for (int i = 0; i < savedBookingRequests.size(); i++) {
                var currentBookingRequest = savedBookingRequests.get(i);
                System.out.println("Request " + i + ": Client name: " + currentBookingRequest.getClient().getName()
                        + " Room class: " + currentBookingRequest.getRoomClass() + " On days: " + currentBookingRequest.getDays());
            }

            var processedRequests = admin.processRequests(savedBookingRequests);

            var fileOutputStreamForClients = new FileOutputStream("clients.ser");

            var clients = new ArrayList<Client>();
            clients.add(firstClient);
            clients.add(secondClient);

            writeObjectsToFile(fileOutputStreamForClients, clients);

            fileOutputStreamForClients.close();

            var savedClients = getClients();

            System.out.println("Deserialized Clients:");
            for (int i = 0; i < savedClients.size(); i++) {
                var currentClient = savedClients.get(i);
                System.out.println("Client " + i + ": " + currentClient.getName());
                currentClient.makePayment(processedRequests.get(i));
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static ArrayList<BookingRequest> getBookingRequests() throws IOException, ClassNotFoundException {
        var fileInputStreamForBookingRequest = new FileInputStream("booking_requests.ser");
        var objectInputStreamForBookingRequest = new ObjectInputStream(fileInputStreamForBookingRequest);

        var firstSavedBookingRequest = (BookingRequest) objectInputStreamForBookingRequest.readObject();
        var secondSavedBookingRequest = (BookingRequest) objectInputStreamForBookingRequest.readObject();

        objectInputStreamForBookingRequest.close();
        fileInputStreamForBookingRequest.close();

        var savedBookingRequests = new ArrayList<BookingRequest>();
        savedBookingRequests.add(firstSavedBookingRequest);
        savedBookingRequests.add(secondSavedBookingRequest);
        return savedBookingRequests;
    }

    private static ArrayList<Client> getClients() throws IOException, ClassNotFoundException {
        var fileInputStreamForClients = new FileInputStream("clients.ser");
        var objectInputStreamForClients = new ObjectInputStream(fileInputStreamForClients);

        var firstSavedClient = (Client) objectInputStreamForClients.readObject();
        var secondSavedClient = (Client) objectInputStreamForClients.readObject();

        objectInputStreamForClients.close();
        fileInputStreamForClients.close();

        var savedClients = new ArrayList<Client>();
        savedClients.add(firstSavedClient);
        savedClients.add(secondSavedClient);
        return savedClients;
    }

    private static <T> void writeObjectsToFile(FileOutputStream fileOutputStream, ArrayList<T> objects) {
        try {
            var objectOutputStreamForBookingRequest = new ObjectOutputStream(fileOutputStream);

            for (var object : objects) {
                objectOutputStreamForBookingRequest.writeObject(object);
            }

            objectOutputStreamForBookingRequest.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}