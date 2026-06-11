package ru.demo.task.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.demo.task.domain.task.Task;
import ru.demo.task.service.TaskService;
import ru.demo.task.web.dto.task.TaskDto;
import ru.demo.task.web.dto.validation.OnUpdate;
import ru.demo.task.web.mappers.TaskMapper;

@RestController
@RequestMapping("api/v1/task")
@RequiredArgsConstructor
@Validated
@Tag(name = "Task Controller", description = "Task api")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @PutMapping
    @Operation(summary = "Update task")
    public TaskDto update(@Validated(OnUpdate.class) @RequestBody TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        Task updatedTask = taskService.update(task);
        return taskMapper.toDto(updatedTask);
    }

    @Operation(summary = "Get task")
    @GetMapping("/{id}")
    public TaskDto getById(@PathVariable Long id) {
        Task task = taskService.getById(id);
        return taskMapper.toDto(task);
    }

    @Operation(summary = "Delete task")
    @DeleteMapping("/{id}")
    public void deleteByID(@PathVariable Long id) {
        taskService.delete(id);
    }
}
