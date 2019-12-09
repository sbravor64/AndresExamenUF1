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

public class AfegeixIdFilms{
    static String directorioXML = "filmsexamen.xml";

    public static void main(String[] args) {

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(directorioXML);

            document.getDocumentElement().normalize();

            System.out.println("Root element: " + document.getDocumentElement().getNodeName());
            NodeList nlist = document.getElementsByTagName("Film");

            for (int i = 0; i < nlist.getLength() ; i++) {
                Node nNode = nlist.item(i);
                Element element = (Element) nNode;
                Attr id = document.createAttribute("id");
                System.out.println(i);
                id.setValue("film " + i);

                element.setAttributeNode(id);
                saveFile(document);

            }

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
}
