package ru.demo.task.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.demo.task.domain.exeption.ResourceMappingException;
import ru.demo.task.domain.task.Task;
import ru.demo.task.repository.DataSourceConfig;
import ru.demo.task.repository.TaskRepository;
import ru.demo.task.repository.mappers.TaskRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {
    private final DataSourceConfig dataSourceConfig;
    private final String FIND_BY_ID = """
            SELECT t.id as task_id,
            	   t.title as task_title,
            	   t.description as task_description,
            	   t.expiration_date as task_expiration
            from tasks t
            where t.id = ?
            """;

    private final String FIND_ALL_BY_USER_ID = """
            SELECT t.id as task_id,
            	   t.title as task_title,
            	   t.description as task_description,
            	   t.expiration_date as task_expiration
            from tasks t
            left join users_tasks u on t.id = u.task_id
            where t.id = ?
            """;

    private final String ASSIGNED_BY_USER_ID = """
            INSERT INTO users_tasks(task_id, user_id)
            VALUES (?, ?)
            """;

    private final String DELETE = """
            DELETE from tasks
            where id = ?
            """;

    private final String UPDATE = """
            UPDATE tasks
            SET title = ?,
            description = ?,
            expiration_date = ?,
            status = ?
            """;

    private final String CREATE = """
            INSERT INTO tasks(title, description, expiration_date, status)
            VALUES(?,?,?,?)
            """;

    @Override
    public Optional<Task> findById(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                return Optional.ofNullable(TaskRowMapper.mapRow(rs));
            }
        } catch (SQLException throwables) {
            throw new ResourceMappingException(String.format("Error while finding user by id. Error сode %s", throwables.getErrorCode()));
        }
    }

    @Override
    public List<Task> findAllByUserId(Long userId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_USER_ID);
            statement.setLong(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                return TaskRowMapper.mapRows(rs);
            }
        } catch (SQLException throwables) {
            throw new ResourceMappingException(String.format("Error while finding user by id. Error сode %s", throwables.getErrorCode()));
        }
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
