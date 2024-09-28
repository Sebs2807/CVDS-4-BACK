package edu.eci.cvds.Tasks.repository;

import edu.eci.cvds.Tasks.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
    
}