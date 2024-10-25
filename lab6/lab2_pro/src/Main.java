import entities.Administrator;
import entities.BookingRequest;
import entities.Client;
import xmlHelpers.parsers.BookingRequestSAXParser;
import xmlHelpers.parsers.ClientSAXParser;
import xmlHelpers.writers.XMLWriter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        XMLWriter xmlWriter = new XMLWriter();

        var admin = new Administrator("Gleb");

        var firstClient = new Client("Alice");
        var secondClient = new Client("Bob");

        var clients = new ArrayList<Client>();
        clients.add(firstClient);
        clients.add(secondClient);

        xmlWriter.saveClientsToXml(clients, "D:\\ProgrammingAndProjects\\Studies\\5sem\\VSRPP\\lab6\\clients.xml");

        var clientsFromXml = ClientSAXParser.parseClients("D:\\ProgrammingAndProjects\\Studies\\5sem\\VSRPP\\lab6\\clients.xml");

        System.out.println("Users:");
        for (int i = 0; i < clientsFromXml.size(); i++) {
            var currentClient = clientsFromXml.get(i);
            System.out.println("Client id: " + currentClient.getId() + "; name: " + currentClient.getName());
        }
        System.out.println("---------------------------------------------------------");

        var firstBookingRequest = clientsFromXml.get(0).createBookingRequest(3, "lux", 5);
        var secondBookingRequest = clientsFromXml.get(1).createBookingRequest(2, "standard", 3);

        var bookingRequests = new ArrayList<BookingRequest>();
        bookingRequests.add(firstBookingRequest);
        bookingRequests.add(secondBookingRequest);

        xmlWriter.saveBookingRequestsToXml(bookingRequests, "D:\\ProgrammingAndProjects\\Studies\\5sem\\VSRPP\\lab6\\booking-requests.xml");

        System.out.println("Requests:");
        var bookingRequestsFromXml = BookingRequestSAXParser.parseBookingRequests("D:\\ProgrammingAndProjects\\Studies\\5sem\\VSRPP\\lab6\\booking-requests.xml");
        for (int i = 0; i < bookingRequestsFromXml.size(); i++) {
            var currentBookingRequest = bookingRequestsFromXml.get(i);
            System.out.println("Request " + currentBookingRequest.getId() + ": Client name: " + currentBookingRequest.getClient().getName()
                    + " Room class: " + currentBookingRequest.getRoomClass() + " On days: "
                    + currentBookingRequest.getDays() + " Confirmed: " + currentBookingRequest.isConfirmed());
        }
        System.out.println("---------------------------------------------------------");

        System.out.println("Processing request:");
        var processedRequests = admin.processRequests(bookingRequests);
        System.out.println("---------------------------------------------------------");

        xmlWriter.saveBookingRequestsToXml(processedRequests, "D:\\ProgrammingAndProjects\\Studies\\5sem\\VSRPP\\lab6\\processed-booking-requests.xml");

        var processedBookingRequestsFromXml = BookingRequestSAXParser.parseBookingRequests("D:\\ProgrammingAndProjects\\Studies\\5sem\\VSRPP\\lab6\\processed-booking-requests.xml");

        System.out.println("Processed request:");
        for (int i = 0; i < processedBookingRequestsFromXml.size(); i++) {
            var currentBookingRequest = processedBookingRequestsFromXml.get(i);
            System.out.println("Request " + currentBookingRequest.getId() + ": Client name: " + currentBookingRequest.getClient().getName()
                    + " Room class: " + currentBookingRequest.getRoomClass() + " On days: "
                    + currentBookingRequest.getDays() + " Confirmed: " + currentBookingRequest.isConfirmed());
        }
        System.out.println("---------------------------------------------------------");

        System.out.println("Processed request payment:");
        for (int i = 0; i < processedBookingRequestsFromXml.size(); i++) {
            var currentBookingRequest = processedBookingRequestsFromXml.get(i);
            currentBookingRequest.makePayment();
        }
        System.out.println("---------------------------------------------------------");
    }
}