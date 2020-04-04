package com.uagrm.auxiliaturasya_php;

public class Materia {
    String nombreMateria,nombreFacultad;

    public Materia(){
        nombreFacultad=""; nombreMateria="";
    }

    public Materia(String nombreMateria, String nombreFacultad) {
        this.nombreMateria = nombreMateria;
        this.nombreFacultad = nombreFacultad;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getNombreFacultad() {
        return nombreFacultad;
    }

    public void setNombreFacultad(String nombreFacultad) {
        this.nombreFacultad = nombreFacultad;
    }
}
