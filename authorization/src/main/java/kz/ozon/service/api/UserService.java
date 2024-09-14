package kz.ozon.service.api;


import kz.ozon.dto.UserDto;
import kz.ozon.dto.UserShortDto;

import java.util.List;

public interface UserService {
    /**
     * Finds a user by their ID.
     *
     * @param id The ID of the user to find.
     * @return The UserShortDto object representing the user with the given ID.
     */
    UserShortDto findById(String id);

    /**
     * Updates the user with the given ID using the provided UserDto.
     *
     * @param id       The ID of the user to update.
     * @param userDto  The UserDto containing the updated user information.
     * @return The UserShortDto object representing the updated user.
     */
    UserShortDto updateUser(String id, UserDto userDto);

    void deleteById(String id);

    List<UserShortDto> getAllUsers(int from, int size);
}
