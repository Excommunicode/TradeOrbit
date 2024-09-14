package kz.ozon.service.api;

import jakarta.security.auth.message.AuthException;
import kz.ozon.dto.JwtRequest;
import kz.ozon.dto.JwtResponse;
import kz.ozon.dto.RefreshJwtRequest;
import kz.ozon.dto.RegisterUserRequest;
import kz.ozon.jwt.JwtAuthentication;


public interface AuthService {
    /**
     * Registers a new user.
     *
     * @param request the register user request with the user information
     * @return the JWT response containing the access token and refresh token
     */
    JwtResponse register(RegisterUserRequest request) ;

    /**
     * Authenticates the user with the given credentials and returns a JWT response.
     *
     * @param authRequest the authentication request containing the user's login and password
     * @return a JWT response containing the access token and refresh token
     * @throws AuthException if the authentication fails
     */
    JwtResponse login(JwtRequest authRequest) throws AuthException;

    /**
     * Retrieves an access token using a refresh token.
     *
     * @param refreshToken The refresh token used to obtain a new access token.
     * @return A JwtResponse object containing the new access token and refresh token.
     * @throws AuthException if there is an error retrieving the access token.
     */
    JwtResponse getAccessToken(String refreshToken) throws AuthException;

    /**
     * Refreshes the access token using the provided refresh token.
     *
     * @param refreshToken The refresh token used to generate a new access token.
     * @return The refreshed access token and refresh token encapsulated in a JwtResponse object.
     * @throws AuthException If an error occurs during the refresh process.
     */
    JwtResponse refresh(String refreshToken) throws AuthException;

    /**
     * Retrieves the authentication information for the current user.
     *
     * @return The JwtAuthentication object containing the authentication details.
     */
    JwtAuthentication getAuthInfo();

    /**
     * Logs out the user by invalidating the refresh token.
     *
     * @param request the refresh token request
     */
    void logout(RefreshJwtRequest request);
}
