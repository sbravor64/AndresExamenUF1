package com.company;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class AfegeixEquip {

    static String directorioXML = "equipsXML.xml";

    public static void main(String[] args) {
        addEquip();
    }

    static void addEquip() {
        try{
            addElements(createDoc());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Document createDoc(){
        Document document = null;
        try{
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(directorioXML);
            document.getDocumentElement().normalize();
        }catch (Exception e){
            e.printStackTrace();
        }
        return document;
    }

    public static void addElements(Document document) throws TransformerException {

        Element rootElement = document.getDocumentElement();

        Element equipo = document.createElement("equipo");
        Attr apodo = document.createAttribute("apodo");
        apodo.setValue("equipo4");

        equipo.setAttributeNode(apodo);
        rootElement.appendChild(equipo);

        Element nom = document.createElement("nom");
        nom.appendChild(document.createTextNode("equipo4"));
        equipo.appendChild(nom);

        Element lliga = document.createElement("lliga");
        lliga.appendChild(document.createTextNode("liga4"));
        equipo.appendChild(lliga);

        Element classificacio = document.createElement("classificacio");
        classificacio.appendChild(document.createTextNode("4"));
        equipo.appendChild(classificacio);

        saveFile(document);
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
