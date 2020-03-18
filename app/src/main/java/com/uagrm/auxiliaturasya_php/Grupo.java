package com.uagrm.auxiliaturasya_php;

public class Grupo {
    String nombreGrupo,nombreMateria,dia,hora,fechaIni,fechaFin;


    public Grupo(String nombreGrupo, String nombreMateria, String dia, String hora, String fechaIni, String fechaFin) {
        this.nombreGrupo = nombreGrupo;
        this.nombreMateria = nombreMateria;
        this.dia = dia;
        this.hora = hora;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
}
