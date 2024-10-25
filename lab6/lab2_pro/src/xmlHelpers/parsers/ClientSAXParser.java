package xmlHelpers.parsers;

import entities.Client;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;

public class ClientSAXParser extends DefaultHandler {
    private List<Client> clients = new ArrayList<>();
    private String currentId;
    private String currentName;
    private StringBuilder currentValue = new StringBuilder();

    public List<Client> getClients() {
        return clients;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentValue.setLength(0);
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
                currentName = currentValue.toString();
                break;
            case "Client":
                Client client = new Client(currentId, currentName);
                clients.add(client);
                break;
        }
    }

    public static List<Client> parseClients(String xmlFilePath) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            ClientSAXParser handler = new ClientSAXParser();
            saxParser.parse(xmlFilePath, handler);
            return handler.getClients();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
