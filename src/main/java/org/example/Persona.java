package org.example;

public class Persona {

    private int id;
    private String nombre;
    private String apellido;
    private int edad;

    private static int contador = 1;

    public Persona(String nombre, String apellido, int edad) {
        this.id = contador++;
        this.nombre = nombre;
        this.edad = edad;
        this.apellido = apellido;
    }

    public int getId() {
        return id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}