package com.uagrm.auxiliaturasya_php;

public class Materia {
    String nombreMateria,nombreFacultad,imagen;

    public Materia(){
        nombreFacultad=""; nombreMateria=""; imagen="";
    }

    public Materia(String nombreMateria, String nombreFacultad, String imagen) {
        this.nombreMateria = nombreMateria;
        this.nombreFacultad = nombreFacultad;
        this.imagen = imagen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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
