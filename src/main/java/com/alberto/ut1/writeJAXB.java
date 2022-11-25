package com.alberto.ut1;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.*;

public class writeJAXB {
    private static final int size = 50;
    public static void main(String[] args) {
        Futbolista f = new Futbolista(0,"si",0);
        //ArrayList<Futbolista> futbolistas = new ArrayList<>();
        Futbolistas futbolistas = new Futbolistas();
        try(RandomAccessFile raf = new RandomAccessFile("futbol.dat","rw");){
            try{
                for(int i=1;i<raf.length();i++) {
                    raf.seek((long)(i-1)*size);
                    int id = raf.readInt();
                    if(i==id) {
                        String nombre = raf.readUTF();
                        double media = raf.readDouble();
                        f = new Futbolista(id, nombre, media);
                        futbolistas.addFutbolista(f);
                    }
                }
            }
            catch(EOFException i){
            }
            catch(IOException e2){
                e2.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            JAXBContext contexto = JAXBContext.newInstance(futbolistas.getClass());
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            marshaller.marshal(futbolistas,new File("futbolistaJAXB.xml"));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
