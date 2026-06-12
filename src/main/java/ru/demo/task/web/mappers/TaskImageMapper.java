package ru.demo.task.web.mappers;

import org.mapstruct.Mapper;
import ru.demo.task.domain.task.TaskImage;
import ru.demo.task.web.dto.task.TaskImageDto;

@Mapper(componentModel = "spring")
public interface TaskImageMapper extends Mappable<TaskImage, TaskImageDto> {
}
