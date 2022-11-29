package com.example.evidencia;

public class Reason {
    String persona;
    String alias;
    String nombre;
    String direccion;
    String email;
    String telefono;
    String rfc;

    public Reason(){
        this.persona = "";
        this.alias = "";
        this.nombre = "";
        this.direccion = "";
        this.email = "";
        this.telefono = "";
        this.rfc = "";
    }

    public Reason(String person, String alias, String nombre, String direccion, String email, String telefono, String rfc){
        this.persona = person;
        this.alias = alias;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.rfc = rfc;
    }
}

