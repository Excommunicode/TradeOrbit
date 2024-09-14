package kz.ozon.repository;

import kz.ozon.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * The RefreshTokenRepository interface represents a repository for managing RefreshTokens in the system.
 * It extends the JpaRepository interface to inherit basic CRUD functionality.
 */
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    /**
     * Finds a refresh token by the user's login.
     *
     * @param login the user's login
     * @return an Optional containing the RefreshToken if found, or an empty Optional if not found
     */
    @Query("SELECT r " +
            "FROM RefreshToken r " +
            "JOIN FETCH r.user u " +
            "WHERE u.login = :login")
    Optional<RefreshToken> findByUserLogin(String login);

    /**
     * Deletes a refresh token by its token value.
     *
     * @param token the token value of the refresh token to delete
     * @return the number of refresh tokens deleted
     */
    @Modifying
    @Query("DELETE FROM RefreshToken r WHERE r.token = :token")
    int deleteByToken(String token);

    /**
     * Deletes all RefreshToken objects whose expiryDate is before the specified date and time.
     *
     * @param now The current date and time.
     * @return The number of RefreshToken objects deleted.
     */
    @Modifying
    @Query("DELETE FROM RefreshToken r WHERE r.expiryDate < :now")
    int deleteAllByExpiryDateIsBefore(LocalDateTime now);


    /**
     * Deletes refresh tokens based on the user ID.
     *
     * @param id The ID of the user to delete refresh tokens for.
     */
    @Modifying
    @Query("DELETE FROM RefreshToken r WHERE r.user.id = :id")
    void deleteByUserId(String id);
}