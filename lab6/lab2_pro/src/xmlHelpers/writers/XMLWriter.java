package xmlHelpers.writers;

import entities.BookingRequest;
import entities.Client;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

    public class XMLWriter {
        public void saveClientsToXml(List<Client> clients, String filePath) throws ParserConfigurationException, TransformerException {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element rootElement = document.createElement("Clients");
            document.appendChild(rootElement);

            for (Client client : clients) {
                Element clientElement = createClientElement(client, document);
                rootElement.appendChild(clientElement);
            }

            writeDocumentToFile(document, filePath);
        }

        private Element createClientElement(Client client, Document document) {
            Element clientElement = document.createElement("Client");

            Element clientId = document.createElement("Id");
            clientId.appendChild(document.createTextNode(client.getId()));

            Element clientName = document.createElement("Name");
            clientName.appendChild(document.createTextNode(client.getName()));

            clientElement.appendChild(clientId);
            clientElement.appendChild(clientName);

            return clientElement;
        }

        public void saveBookingRequestsToXml(List<BookingRequest> requests, String filePath) throws ParserConfigurationException, TransformerException {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element rootElement = document.createElement("BookingRequests");
            document.appendChild(rootElement);

            for (BookingRequest request : requests) {
                Element requestElement = createBookingRequestElement(request, document);
                rootElement.appendChild(requestElement);
            }

            writeDocumentToFile(document, filePath);
        }

        private Element createBookingRequestElement(BookingRequest request, Document document) {
            Element requestElement = document.createElement("BookingRequest");

            Element requestId = document.createElement("Id");
            requestId.appendChild(document.createTextNode(request.getId()));

            Element clientElement = createClientElement(request.getClient(), document);
            requestElement.appendChild(clientElement);

            Element places = document.createElement("Places");
            places.appendChild(document.createTextNode(String.valueOf(request.getPlaces())));

            Element roomClass = document.createElement("RoomClass");
            roomClass.appendChild(document.createTextNode(request.getRoomClass()));

            Element days = document.createElement("Days");
            days.appendChild(document.createTextNode(String.valueOf(request.getDays())));

            Element confirmed = document.createElement("Confirmed");
            confirmed.appendChild(document.createTextNode(String.valueOf(request.isConfirmed())));

            Element paid = document.createElement("Paid");
            paid.appendChild(document.createTextNode(String.valueOf(request.isPaid())));

            requestElement.appendChild(requestId);
            requestElement.appendChild(places);
            requestElement.appendChild(roomClass);
            requestElement.appendChild(days);
            requestElement.appendChild(confirmed);
            requestElement.appendChild(paid);

            return requestElement;
        }

        private void writeDocumentToFile(Document document, String filePath) throws TransformerException {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(filePath));

            transformer.transform(domSource, streamResult);
        }
}
