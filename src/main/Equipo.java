package org.example;

public class Equipo {
    public Equipo(String nombre, int id){
        this.nombre = nombre;
        this.id=id;
    }

    private String nombre;
    private int id;

    public String getNombre() {
        return nombre;
    }

}