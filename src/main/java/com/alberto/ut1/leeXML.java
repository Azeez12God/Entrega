package com.alberto.ut1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class leeXML {
    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document doc = null;
        try {
            doc = db.parse(new File("futbolistas.xml"));
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        NodeList futbolistas = doc.getElementsByTagName("futbolista");
        for (int i = 0; i < futbolistas.getLength(); i++) {
            Node futbolista = futbolistas.item(i);
            Element elemento = (Element) futbolista;
            System.out.println(elemento.getElementsByTagName("id").item(0)
                    .getTextContent());
            System.out.println(elemento.getElementsByTagName("nombre").item(0)
                    .getTextContent());
            System.out.println(elemento.getElementsByTagName("media_goles").item(0)
                    .getTextContent());
            System.out.println();
        }
    }
}
