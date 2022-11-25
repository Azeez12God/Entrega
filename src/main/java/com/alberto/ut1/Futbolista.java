package com.alberto.ut1;

public class Futbolista {

    private int idJug;
    private String nombre;
    private double mediaGoles;

    public Futbolista(int idJug, String nombre, double mediaGoles) {
        this.idJug = idJug;
        this.nombre = nombre;
        this.mediaGoles = mediaGoles;
    }

    public int getIdJug() {
        return idJug;
    }

    public void setIdJug(int idJug) {
        this.idJug = idJug;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMediaGoles() {
        return mediaGoles;
    }

    public void setMediaGoles(double mediaGoles) {
        this.mediaGoles = mediaGoles;
    }
}
