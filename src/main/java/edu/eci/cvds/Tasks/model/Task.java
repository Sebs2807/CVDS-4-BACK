package edu.eci.cvds.Tasks.model;

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
    private Integer tiempoTarea;
    private String idUser;

    /**
     * Obtiene la prioridad de la tarea.
     * @return un valor entero que representa la prioridad de la tarea.
     */
    public Integer getPrioridadTarea() {
        return prioridadTarea;
    }

    /**
     * Asigna la prioridad de la tarea.
     * @param prioridadTarea un valor entero que representa la prioridad de la tarea.
     */
    public void setPrioridadTarea(Integer prioridadTarea) {
        this.prioridadTarea = prioridadTarea;
    }

    /**
     * Obtiene la dificultad de la tarea.
     * @return una instancia de Difficulty que representa la dificultad de la tarea.
     */
    public Difficulty getDificultadTarea() {
        return dificultadTarea;
    }

    /**
     * Asigna la dificultad de la tarea.
     * @param dificultadTarea una instancia de Difficulty que representa la dificultad de la tarea.
     */
    public void setDificultadTarea(Difficulty dificultadTarea) {
        this.dificultadTarea = dificultadTarea;
    }

    /**
     * Obtiene el tiempo estimado para la tarea.
     * @return un valor entero que representa el tiempo estimado en horas para la tarea.
     */
    public Integer getTiempoTarea() {
        return tiempoTarea;
    }

    /**
     * Asigna el tiempo estimado para la tarea.
     * @param tiempoTarea un valor entero que representa el tiempo estimado en horas para la tarea.
     */
    public void setTiempoTarea(Integer tiempoTarea) {
        this.tiempoTarea = tiempoTarea;
    }

    /**
     * Obtiene el ID de la tarea.
     * @return el ID de la tarea.
     */
    public String getIdTarea() {
        return idTarea;
    }

    /**
     * Asigna el ID de la tarea.
     * @param idTarea el ID de la tarea.
     */
    public void setIdTarea(String idTarea) {
        this.idTarea = idTarea;
    }

    /**
     * Obtiene el nombre de la tarea.
     * @return el nombre de la tarea.
     */
    public String getNombreTarea() {
        return nombreTarea;
    }

    /**
     * Asigna el nombre de la tarea.
     * @param nombreTarea el nombre de la tarea.
     */
    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    /**
     * Verifica si la tarea ha sido finalizada.
     * @return true si la tarea está finalizada, false en caso contrario.
     */
    public boolean isFinalizada() {
        return finalizada;
    }

    /**
     * Establece el estado de finalización de la tarea.
     * @param finalizada true si la tarea está finalizada, false en caso contrario.
     */
    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    /**
     * Obtiene la descripción de la tarea.
     * @return la descripción de la tarea.
     */
    public String getDescTarea() {
        return descTarea;
    }

    /**
     * Asigna la descripción de la tarea.
     * @param descTarea la descripción de la tarea.
     */
    public void setDescTarea(String descTarea) {
        this.descTarea = descTarea;
    }

    /**
     * Obtiene el ID del usuario asociado a esta tarea.
     * @return el ID del usuario.
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     * Asigna el ID del usuario asociado a esta tarea.
     * @param idUser el ID del usuario.
     */
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
