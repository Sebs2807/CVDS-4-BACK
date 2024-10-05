package edu.eci.cvds.Tasks.model;

import java.time.Duration;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
public class Task {

    @Id
    private String idTarea;

    private String nombreTarea;
    private boolean finalizada;
    private String descTarea;
    private Integer prioridadTarea;
    private Difficulty dificultadTarea;
    private Duration tiempoTarea;

    public Integer getPrioridadTarea() {
        return prioridadTarea;
    }

    public void setPrioridadTarea(Integer prioridadTarea) {
        this.prioridadTarea = prioridadTarea;
    }

    public Difficulty getDificultadTarea() {
        return dificultadTarea;
    }

    public void setDificultadTarea(Difficulty dificultadTarea) {
        this.dificultadTarea = dificultadTarea;
    }

    public Duration getTiempoTarea() {
        return tiempoTarea;
    }

    public void setTiempoTarea(Duration tiempoTarea) {
        this.tiempoTarea = tiempoTarea;
    }

    public String getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(String idTarea) {
        this.idTarea = idTarea;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    public String getDescTarea() {
        return descTarea;
    }

    public void setDescTarea(String descTarea) {
        this.descTarea = descTarea;
    }
}
