package com.company;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class AfegeixBooks {

    static String directorioXML = "filmsexamen.xml";

    public static void main(String[] args) {

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(directorioXML);

            Element element = document.getDocumentElement();
            Element library = document.createElement("Library");

            while (element.hasChildNodes()) {
                library.appendChild(element.getFirstChild());
            }

            element.getParentNode().replaceChild(library, element);
            añadirBook(document);

            saveFile(document);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void saveFile(Document doc){
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(directorioXML);
            transformer.transform(domSource, streamResult);
        }catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void añadirBook(Document document){

        Element root = document.getDocumentElement();
        Element book = document.createElement("Book");
        Element title = document.createElement("Title");
        title.appendChild(document.createTextNode("ElOscareselmejor"));
//        Element author = document.createElement(" Author");
//        author.appendChild(document.createTextNode("ElOscareselmejor"));

        Element counthy = document.createElement("Country");
        counthy.appendChild(document.createTextNode("ElOscareselmejor"));

        Attr published = document.createAttribute("published");
        published.setValue("2019");
        book.setAttributeNode(published);

        root.appendChild(book);
        book.appendChild(title);
//        book.appendChild(author);
        book.appendChild(counthy);
    }
}
