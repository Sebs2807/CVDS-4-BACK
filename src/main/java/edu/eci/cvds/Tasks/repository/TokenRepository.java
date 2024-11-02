package edu.eci.cvds.Tasks.repository;

import edu.eci.cvds.Tasks.model.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interfaz de repositorio para gestionar entidades {@link Token}.
 * Extiende la interfaz {@link MongoRepository} para proporcionar operaciones
 * CRUD.
 */
public interface TokenRepository extends MongoRepository<Token, String> {
  // No se requieren métodos adicionales, ya que MongoRepository proporciona las
  // operaciones CRUD básicas.
}