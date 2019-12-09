package com.company;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

class FilmsHandler extends DefaultHandler {
    boolean bTitle = false;
    boolean bDirector = false;
    boolean bCountry = false;
    int i=1;

    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName.toLowerCase()) {
            case "film":
                System.out.println("----------------------");
                System.out.println("Film " + i++);
                String codi = attributes.getValue("produced");
                System.out.println("produced: " + codi);
                break;
            case "title":
                bTitle = true;
                break;
            case "director":
                bDirector = true;
                break;
            case "country":
                bCountry = true;
                break;
        }
    }

    public void characters(char ch[], int start, int length) {
        if (bTitle) {
            System.out.println("Title: " + new String(ch, start, length));
            bTitle = false;
        } else if (bDirector) {
            System.out.println("Director: " + new String(ch, start, length));
            bDirector = false;
        } else if (bCountry) {
            System.out.println("Country: " + new String(ch, start, length));
            bCountry = false;
        }

    }
}

public class ReadFilms {

    static String directorioXML = "filmsexamen.xml";

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        readFilms();
    }

    static void readFilms() throws IOException, SAXException, ParserConfigurationException {
        File inputFile = new File(directorioXML);
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser parser = saxParserFactory.newSAXParser();
        FilmsHandler alumneHandler = new FilmsHandler();
        parser.parse(inputFile, alumneHandler);
    }
}
