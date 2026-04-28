package ru.demo.task.web.mappers;

import org.mapstruct.Mapper;
import ru.demo.task.domain.user.User;
import ru.demo.task.web.dto.user.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
