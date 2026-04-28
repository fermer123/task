package ru.demo.task.service.impl;

import org.springframework.stereotype.Service;
import ru.demo.task.domain.task.Task;
import ru.demo.task.service.TaskService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Override
    public Task getById(Long id) {
        return null;
    }

    @Override
    public List<Task> getAllByUserId(Long id) {
        return List.of();
    }

    @Override
    public Task update(Task task) {
        return null;
    }

    @Override
    public Task create(Task task, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
