package kz.ozon.service;


import kz.ozon.dto.UserDto;
import kz.ozon.dto.UserShortDto;
import kz.ozon.exception.NotFoundException;
import kz.ozon.mapper.UserMapper;
import kz.ozon.model.User;
import kz.ozon.repository.RefreshTokenRepository;
import kz.ozon.repository.UserRepository;
import kz.ozon.service.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;


    @Override
    @Transactional
    public UserShortDto updateUser(String id, UserDto userDto) {
        UUID uuid = getUuid(id);
        User user = getUser(uuid);

        userDto.setPassword(passwordEncoder.encode(user.getPassword()));
        User updateUser = userMapper.updateUser(user, userDto);
        User save = userRepository.save(updateUser);

        return userMapper.toShortDto(save);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        UUID uuid = getUuid(id);

        userRepository.deleteById(uuid);
        if (userRepository.existsById(uuid)) {
            throw NotFoundException.builder()
                    .message(String.format("User with id: %s was not deleted", uuid))
                    .build();
        }
        refreshTokenRepository.deleteByUserId(id);
    }

    @Override
    public List<UserShortDto> getAllUsers(int from, int size) {
        PageRequest pageRequest = PageRequest.of(from, size);
        return userMapper.toListUserShortDto(userRepository.findAll(pageRequest).getContent());
    }


    @Override
    public UserShortDto findById(String id) {
        UUID uuid = getUuid(id);
        return getUserShortDto(uuid);
    }


    private UserDto getUserDto(UUID uuid) {
        User user = getUser(uuid);
        return userMapper.toUserDto(user);
    }

    private UUID getUuid(String id) {
        return UUID.fromString(id);
    }

    private UserShortDto getUserShortDto(UUID uuid) {
        return userMapper.toShortDto(getUser(uuid));
    }

    private User getUser(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(() -> NotFoundException.builder()
                .message(String.format("User with id: %s not found", uuid))
                .build());
    }
}
