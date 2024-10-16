package edu.eci.cvds.Tasks.repository;

import edu.eci.cvds.Tasks.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByuserName(String username);
}
