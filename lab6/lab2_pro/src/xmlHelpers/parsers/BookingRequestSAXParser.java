package xmlHelpers.parsers;

import entities.BookingRequest;
import entities.Client;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;

public class BookingRequestSAXParser extends DefaultHandler {
    private List<BookingRequest> bookingRequests = new ArrayList<>();
    private String currentId;
    private Client currentClient;
    private int currentPlaces;
    private String currentRoomClass;
    private int currentDays;
    private boolean currentConfirmed;
    private boolean currentPaid;
    private StringBuilder currentValue = new StringBuilder();

    public List<BookingRequest> getBookingRequests() {
        return bookingRequests;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentValue.setLength(0);
        if ("Client".equals(qName)) {
            currentClient = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentValue.append(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "Id":
                currentId = currentValue.toString();
                break;
            case "Name":
                currentClient = new Client(currentId, currentValue.toString());
                break;
            case "Places":
                currentPlaces = Integer.parseInt(currentValue.toString());
                break;
            case "RoomClass":
                currentRoomClass = currentValue.toString();
                break;
            case "Days":
                currentDays = Integer.parseInt(currentValue.toString());
                break;
            case "Confirmed":
                currentConfirmed = Boolean.parseBoolean(currentValue.toString());
                break;
            case "Paid":
                currentPaid = Boolean.parseBoolean(currentValue.toString());
                break;
            case "BookingRequest":
                BookingRequest bookingRequest = new BookingRequest(currentId, currentClient, currentPlaces, currentRoomClass, currentDays);
                bookingRequest.setConfirmed(currentConfirmed);
                bookingRequest.setPaid(currentPaid);
                bookingRequests.add(bookingRequest);
                break;
        }
    }

    public static List<BookingRequest> parseBookingRequests(String xmlFilePath) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            BookingRequestSAXParser handler = new BookingRequestSAXParser();
            saxParser.parse(xmlFilePath, handler);
            return handler.getBookingRequests();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
