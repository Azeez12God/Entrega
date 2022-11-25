package com.alberto.ut1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Futbolistas {
    private ArrayList<Futbolista> futbolistas = null;

    public Futbolistas() {
        futbolistas=new ArrayList<>();
    }

    public void addFutbolista(Futbolista f){
        futbolistas.add(f);
    }

    public void muestraFutbolista(){
        for(Futbolista f:futbolistas)
            System.out.println(f);
    }


}
