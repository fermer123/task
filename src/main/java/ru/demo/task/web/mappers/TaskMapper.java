package ru.demo.task.web.mappers;

import org.mapstruct.Mapper;
import ru.demo.task.domain.task.Task;
import ru.demo.task.web.dto.task.TaskDto;

@Mapper(componentModel = "spring")
public interface TaskMapper extends Mappable<Task, TaskDto> {
}
