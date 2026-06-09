package ru.demo.task.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.demo.task.domain.exeption.ResourceMappingException;
import ru.demo.task.domain.user.Role;
import ru.demo.task.domain.user.User;
import ru.demo.task.repository.DataSourceConfig;
import ru.demo.task.repository.UserRepository;
import ru.demo.task.repository.mappers.UserRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final DataSourceConfig dataSourceConfig;

    private final String FIND_BY_ID = """
             SELECT t.id as user_id,
            	   u.name as user_name,
            	   u.username as user_name,
            	   u.password as user_password,
            	   u.role as user_role_role,
            	   t.id as task_id,
            	   t.tile as task_title,
            	   t.desciption as task_description,
            	   t.expiration_date as task_expiration_date,
            	   t.status as task_status
            from users u
            left join users_roles r on u.id = r.user_id
            left join users_tasks ut on u.id = ut.user_id
            left join tasks r on u.task_id = t.id
            where u.id = ?
            """;

    private final String FIND_BY_USERNAME = """
               SELECT t.id as user_id,
                        	   u.name as user_name,
                        	   u.username as user_name,
                        	   u.password as user_password,
                        	   u.role as user_role_role,
                        	   t.id as task_id,
                        	   t.tile as task_title,
                        	   t.desciption as task_description,
                        	   t.expiration_date as task_expiration_date,
                        	   t.status as task_status
                        from users u
                        left join users_roles r on u.id = r.user_id
                        left join users_tasks ut on u.id = ut.user_id
                        left join tasks r on u.task_id = t.id
                        where u.username = ?
            """;

    private final String UPDATE = """
            update users
            SET name = ?,
            username = ?,
            password = ?,
            where id = ?
            """;

    private final String CREATE = """
            insert into users(name, username, password)
            values = (?,?,?)
            """;

    private final String INSERT_USER_ROLE = """
            insert into users_roles(user_id, role)
            values = (?,?)
            """;
    private final String DELETE = """
            delete from users
            where id = ?
            """;

    private final String IS_TASK_OWNER = """
            select 1
            from users_tasks
            where user_id = ?
            and task_id = ?
            """;


    @Override
    public Optional<User> findById(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                return Optional.ofNullable(UserRowMapper.mapRow(rs));
            }
        } catch (SQLException throwables) {
            throw new ResourceMappingException("Exeption while finding user by id");
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_USERNAME, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, username);
            try (ResultSet rs = statement.executeQuery()) {
                return Optional.ofNullable(UserRowMapper.mapRow(rs));
            }
        } catch (SQLException throwables) {
            throw new ResourceMappingException("Exeption while finding user by username");
        }
    }

    @Override
    public void update(User user) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, user.getName());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getId());
        } catch (SQLException throwables) {
            throw new ResourceMappingException("Exeption while update user.");
        }
    }

    @Override
    public void create(User user) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                rs.next();
                user.setId(rs.getLong(1));
            }
        } catch (SQLException throwables) {
            throw new ResourceMappingException("Exeption while create user.");
        }
    }

    @Override
    public void insertUserRole(Long userId, Role role) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_USER_ROLE);
            statement.setLong(1, userId);
            statement.setString(2, role.name());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new ResourceMappingException("Exeption while inserting user role.");
        }
    }

    @Override
    public boolean isTaskOwner(Long userId, Long taskId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(IS_TASK_OWNER);
            statement.setLong(1, userId);
            statement.setLong(2, taskId);
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                return rs.getBoolean(1);
            }
        } catch (SQLException throwables) {
            throw new ResourceMappingException("Exeption while check user is task owner.");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new ResourceMappingException("Exeption while delete user.");
        }
    }
}
