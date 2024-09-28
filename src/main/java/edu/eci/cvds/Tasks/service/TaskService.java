package edu.eci.cvds.Tasks.service;

import edu.eci.cvds.Tasks.model.Task;
import edu.eci.cvds.Tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Obtener todas las tareas almacenadas en el repositorio.
     *
     * @return Lista de objetos Task que contiene todas las tareas disponibles.
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Obtener una tarea específica por su ID.
     *
     * @param id El identificador único de la tarea.
     * @return Un objeto Optional<Task> que contiene la tarea si es encontrada, o vacío si no existe.
     */
    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id);
    }

    /**
     * Crear una nueva tarea y guardarla en el repositorio.
     *
     * @param task El objeto Task que se va a crear.
     * @return El objeto Task creado y almacenado.
     */
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Actualizar una tarea existente con nuevos valores.
     *
     * @param id El identificador único de la tarea a actualizar.
     * @param updatedTask El objeto Task con los valores actualizados.
     * @return El objeto Task actualizado y almacenado en el repositorio.
     */
    public Task updateTask(String id, Task updatedTask) {
        updatedTask.setIdTarea(id);
        return taskRepository.save(updatedTask);
    }

    /**
     * Eliminar una tarea específica del repositorio utilizando su ID.
     *
     * @param id El identificador único de la tarea a eliminar.
     */
    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }
}
