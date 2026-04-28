package ru.demo.task.web.mappers;

import org.mapstruct.Mapper;
import ru.demo.task.domain.task.Task;
import ru.demo.task.web.dto.task.TaskDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto toDto(Task task);

    List<TaskDto> toDto(List<Task> task);

    Task toEntity(TaskDto dto);
}
