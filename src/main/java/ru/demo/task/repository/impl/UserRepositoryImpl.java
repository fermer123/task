package ru.demo.task.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.demo.task.domain.user.Role;
import ru.demo.task.domain.user.User;
import ru.demo.task.repository.DataSourceConfig;
import ru.demo.task.repository.UserRepository;

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

    @Override
    public Optional<User> findById(Long id) {

    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void create(User user) {

    }

    @Override
    public void insertUserRole(Long userId, Role role) {

    }

    @Override
    public boolean isTaskOwner(Long userId, Long taskId) {
        return false;
    }

    @Override
    public void delete(Long id) {

    }
}
