package com.company;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

public class ParseEquips {
    static String directorioBIN = "equipsBinary.data";
    static String directorioXML = "equipsXML.xml";

    public static void main(String[] args) throws IOException, ClassNotFoundException, TransformerException, ParserConfigurationException {
        ArrayList<Equip> listEquip = new ArrayList<>();

        writeEquipsToXML(listEquip);
        loadWriteEquips(listEquip);
    }

    public static void loadWriteEquips(ArrayList<Equip> listEquips) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(directorioBIN);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Equip equip = (Equip) objectInputStream.readObject();

        try {
            while (true){
                listEquips.add(equip);
                equip = (Equip) objectInputStream.readObject();
            }
        }catch (EOFException e){
            fileInputStream.close();
            objectInputStream.close();
        }

        System.out.println(listEquips);
    }

    public static void writeEquipsToXML(ArrayList<Equip> listEquips) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element rootElement = document.createElement("equipos");
        document.appendChild(rootElement);

        for (Equip equip: listEquips) {

            Element equipo = document.createElement("equipo");
            Attr apodo = document.createAttribute("apodo");
            apodo.setValue(equip.getSobrenom());
            equipo.setAttributeNode(apodo);
            rootElement.appendChild(equipo);

            Element nom = document.createElement("nom");
            nom.appendChild(document.createTextNode(equip.getNom()));
            equipo.appendChild(nom);

            Element lliga = document.createElement("lliga");
            lliga.appendChild(document.createTextNode(equip.getLliga()));
            equipo.appendChild(lliga);

            Element classificacio = document.createElement("classificacio");
            classificacio.appendChild(document.createTextNode(equip.getClassificació()));
            equipo.appendChild(classificacio);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(directorioXML));
            transformer.transform(source,result);
        }
    }
}
