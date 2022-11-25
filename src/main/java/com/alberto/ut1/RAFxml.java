package com.alberto.ut1;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

public class RAFxml {
    private static final int size = 50;
    public static void main(String[] args) {
        Element nodoFutbol,nodoDatos = null;
        Text texto = null;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder db = null;

        try {
            db = dbf.newDocumentBuilder();
        } catch (
                ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        DOMImplementation dom = db.getDOMImplementation();
        Document doc = dom.createDocument(null, "futbolistas",null);

        Element raiz = doc.createElement("futbolista");
        doc.getDocumentElement().appendChild(raiz);

        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile("futbol.dat","rw");
            ArrayList<Futbolista> futbolistas = new ArrayList<>();
            try{
                for(int i=1;i<raf.length();i++) {
                    raf.seek((long)(i-1)*size);
                    int id = raf.readInt();
                    if(i==id) {
                        String nombre = raf.readUTF();
                        double media = raf.readDouble();
                        Futbolista f = new Futbolista(id, nombre, media);
                        futbolistas.add(f);
                    }
                }
            }
            catch(EOFException i){
            }
            catch(IOException e2){
                e2.printStackTrace();
            }
            for(Futbolista f : futbolistas){
                nodoDatos = doc.createElement("id");
                raiz.appendChild(nodoDatos);
                texto= doc.createTextNode(String.valueOf(f.getIdJug()));
                nodoDatos.appendChild(texto);
                nodoDatos = doc.createElement("nombre");
                raiz.appendChild(nodoDatos);
                texto= doc.createTextNode(f.getNombre());
                nodoDatos.appendChild(texto);
                nodoDatos = doc.createElement("media_goles");
                raiz.appendChild(nodoDatos);
                texto= doc.createTextNode(String.valueOf(f.getMediaGoles()));
                nodoDatos.appendChild(texto);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Source source = new DOMSource(doc);
        Result resultado = new StreamResult(new File("futbolistas.xml"));


        try {
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            trans.setOutputProperty("indent", "yes");
            trans.transform(source, resultado);
        } catch (
                TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
