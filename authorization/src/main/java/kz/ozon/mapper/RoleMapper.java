package kz.ozon.mapper;


import kz.ozon.dto.RoleDto;
import kz.ozon.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
         nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleMapper {
    RoleDto toRoleDto(Role role);
}