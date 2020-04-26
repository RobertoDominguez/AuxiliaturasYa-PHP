package com.uagrm.auxiliaturasya_php;

public class Materia {
    String idMateria,nombreMateria,nombreFacultad;

    public Materia(){
        nombreFacultad=""; nombreMateria=""; idMateria="";
    }

    public Materia(String nombreMateria, String nombreFacultad, String idMateria) {
        this.nombreMateria = nombreMateria;
        this.nombreFacultad = nombreFacultad;
        this.idMateria= idMateria;
    }

    public String getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(String idMateria) {
        this.idMateria = idMateria;
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
