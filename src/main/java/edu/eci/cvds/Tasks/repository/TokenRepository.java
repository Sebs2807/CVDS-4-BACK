package edu.eci.cvds.Tasks.repository;

import edu.eci.cvds.Tasks.model.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRepository extends MongoRepository<Token, String> {
}
