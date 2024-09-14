package kz.ozon.mapper;


import kz.ozon.dto.RegisterUserRequest;
import kz.ozon.dto.RoleDto;
import kz.ozon.dto.UserDto;
import kz.ozon.dto.UserShortDto;
import kz.ozon.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    User toUserEntity(RegisterUserRequest request);

    UserShortDto toShortDto(User user);

    UserDto toUserDto(User user);

    @Mapping(target = "id", ignore = true)
    User updateUser(@MappingTarget User user, UserDto userDto);

    default User toUserEntity(RegisterUserRequest request, PasswordEncoder passwordEncoder, Set<RoleDto> roleSet) {
        RegisterUserRequest registerRequest = request.toBuilder()
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roleSet)
                .build();

        return toUserEntity(registerRequest);
    }


    List<UserShortDto> toListUserShortDto(List<User> users);
}