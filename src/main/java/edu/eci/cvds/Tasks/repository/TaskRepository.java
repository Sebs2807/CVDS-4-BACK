package edu.eci.cvds.Tasks.repository;

import edu.eci.cvds.Tasks.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository interface for managing {@link Task} entities.
 * Extends the {@link MongoRepository} interface to provide CRUD operations.
 */
public interface TaskRepository extends MongoRepository<Task, String> {

    /**
     * Finds all tasks associated with a specific user ID.
     *
     * @param idUser the ID of the user whose tasks are to be retrieved.
     * @return a list of tasks associated with the specified user ID.
     */
    List<Task> findByidUser(String idUser);
}