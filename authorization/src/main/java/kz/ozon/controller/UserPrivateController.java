package kz.ozon.controller;

import jakarta.validation.Valid;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import kz.ozon.dto.UserDto;
import kz.ozon.dto.UserShortDto;
import kz.ozon.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "users")
public class UserPrivateController {
    private final UserService userService;

    @PatchMapping("{id}")
    public UserShortDto updateUser(@PathVariable String id, @Valid @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteById(id);
    }

    @GetMapping
    public List<UserShortDto> getUsers(@PositiveOrZero @RequestParam(required = false) int from,
                                       @Positive @RequestParam(required = false) int size) {
        return userService.getAllUsers(from, size);
    }

    @GetMapping("{id}")
    public UserShortDto getUser(@PathVariable String id) {
        return userService.findById(id);
    }
}
