package edu.eci.cvds.Tasks.controller;

import edu.eci.cvds.Tasks.model.Task;
import edu.eci.cvds.Tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * Obtener todas las tareas.
     *
     * @return Lista de objetos Task, que contiene todas las tareas.
     */
    @GetMapping("/GAT/{idUser}")
    public List<Task> getAllTasks(@PathVariable String idUser) {
        return taskService.getAllTasks(idUser);
    }

    /**
     * Obtener una tarea específica por su ID.
     *
     * @param id El identificador único de la tarea.
     * @return Un objeto Task si se encuentra, o null si no existe la tarea con el ID especificado.
     */
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id).orElse(null);
    }

    /**
     * Crear una nueva tarea.
     *
     * @param task El objeto Task que se va a crear, enviado en el cuerpo de la solicitud.
     * @return El objeto Task creado.
     */
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    /**
     * Actualizar una tarea existente.
     *
     * @param id El identificador único de la tarea a actualizar.
     * @param updatedTask El objeto Task actualizado con los nuevos valores.
     * @return El objeto Task actualizado.
     */
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable String id, @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }

    /**
     * Eliminar una tarea por su ID.
     *
     * @param id El identificador único de la tarea a eliminar.
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
    }

    
}
