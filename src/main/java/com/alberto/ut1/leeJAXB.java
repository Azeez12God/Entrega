package com.alberto.ut1;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class leeJAXB {
    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(Futbolistas.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Futbolistas f = (Futbolistas) unmarshaller.unmarshal(new File("futbolistaJAXB.xml"));
            f.muestraFutbolista();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
