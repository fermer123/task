package ru.demo.task.domain.user;

import jakarta.persistence.*;
import lombok.Data;
import ru.demo.task.domain.task.Task;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String username;
    private String password;

    @Transient
    private String passwordConfirmation;

    @Column(name = "role")
    @ElementCollection
    @CollectionTable(name = "users_roles")
    @Enumerated(value = EnumType.STRING)
    private Set<Role> roles;

    @CollectionTable(name = "users_tasks")
    @OneToMany
    @JoinColumn(name = "task_id")
    private List<Task> tasks;

    
}
