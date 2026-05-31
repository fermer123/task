package ru.demo.task.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.demo.task.domain.task.Task;
import ru.demo.task.repository.TaskRepository;
import ru.demo.task.repository.mappers.DataSourceConfig;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {
    private final DataSourceConfig dataSourceConfig;
    
    @Override
    public Optional<Task> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Task> findAllByUserId(Long userId) {
        return List.of();
    }

    @Override
    public void assignToUserById(Long taskId, Long userId) {

    }

    @Override
    public void update(Task task) {

    }

    @Override
    public void create(Task task) {

    }

    @Override
    public void delete(Long id) {

    }
}
