package com.uagrm.auxiliaturasya_php;

public class Auxiliar {
 String id_auxiliar,nombre,apellido,celular,habilitado;

    public Auxiliar(){

    }

    public Auxiliar(String id_auxiliar, String nombre, String apellido, String celular, String habilitado) {
        this.id_auxiliar = id_auxiliar;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.habilitado = habilitado;
    }

    public String getId_auxiliar() {
        return id_auxiliar;
    }

    public void setId_auxiliar(String id_auxiliar) {
        this.id_auxiliar = id_auxiliar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }
}
