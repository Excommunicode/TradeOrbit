package kz.ozon.repository;


import kz.ozon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

/**
 * This interface represents a repository for the User entity. It extends the JpaRepository interface, which provides
 * basic CRUD operations for the User entity. Additionally, it provides custom methods for querying and checking the
 * existence of users based on login and email.
 */
public interface UserRepository extends JpaRepository<User, UUID> {
    /**
     * Finds a user by login.
     *
     * @param name the login name of the user
     * @return an Optional containing the user with matching login if found, otherwise an empty Optional
     */
    @Query("SELECT DISTINCT u, r " +
            "FROM User u " +
            "JOIN FETCH u.roles r " +
            "WHERE u.login = :name ")
    Optional<User> findByLogin(String name);

    /**
     * Checks if a user with the given login exists in the database.
     *
     * @param login The login of the user to check for existence
     * @return {@code true} if a user with the given login exists in the database,
     *         {@code false} otherwise
     */
    @Query("SELECT COUNT(u) > 0 " +
            "FROM User u " +
            "WHERE u.login = :login")
    boolean existsByLogin(String login);

    /**
     * Checks if a user with the given email exists in the database.
     *
     * @param email The email address to check for existence.
     * @return true if a user with the given email exists, false otherwise.
     */
    @Query("SELECT COUNT(u) > 0 " +
            "FROM User u " +
            "WHERE u.email = :email")
    boolean existsByEmail(String email);


}