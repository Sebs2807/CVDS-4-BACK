package edu.eci.cvds.Tasks.repository;

import edu.eci.cvds.Tasks.model.Task;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByidUser(String idUser);
}
