package com.uagrm.auxiliaturasya_php;

public class MateriaAuxiliar {

    String idMateria,nombreMateria,idAuxiliar,esAuxiliarOficial,nombreFacultad;

    public MateriaAuxiliar(String idMateria, String nombreMateria, String idAuxiliar, String esAuxiliarOficial,String nombreFacultad) {
        this.idMateria = idMateria;
        this.nombreMateria = nombreMateria;
        this.idAuxiliar = idAuxiliar;
        this.esAuxiliarOficial=esAuxiliarOficial;
        this.nombreFacultad=nombreFacultad;
    }

    public String getNombreFacultad() {
        return nombreFacultad;
    }

    public void setNombreFacultad(String nombreFacultad) {
        this.nombreFacultad = nombreFacultad;
    }

    public String getEsAuxiliarOficial() {
        return esAuxiliarOficial;
    }

    public void setEsAuxiliarOficial(String esAuxiliarOficial) {
        this.esAuxiliarOficial = esAuxiliarOficial;
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

    public String getIdAuxiliar() {
        return idAuxiliar;
    }

    public void setIdAuxiliar(String idAuxiliar) {
        this.idAuxiliar = idAuxiliar;
    }
}
