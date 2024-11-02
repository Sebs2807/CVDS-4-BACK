package edu.eci.cvds.Tasks.repository;

import edu.eci.cvds.Tasks.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Interfaz de repositorio para gestionar entidades {@link User}.
 * Extiende la interfaz {@link MongoRepository} para proporcionar operaciones
 * CRUD.
 */
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Encuentra un usuario por su nombre de usuario.
     *
     * @param username el nombre de usuario del usuario a encontrar.
     * @return un {@link Optional} que contiene el usuario si se encuentra, o vacío
     *         si no se encuentra.
     */
    Optional<User> findByUserName(String username);
}