package ru.demo.task.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import ru.demo.task.web.dto.validation.OnCreate;
import ru.demo.task.web.dto.validation.OnUpdate;

public class UserDto {
    @NotNull(message = "Id must be not null.", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Name must be not null.", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Name length must be smaller than 255 symbols.")
    private String name;

    @NotNull(message = "Username must be not null.", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Username length must be smaller than 255 symbols.")
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password must be not null.", groups = {OnUpdate.class, OnCreate.class})
    private String password;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password confirmation must be not null.", groups = OnUpdate.class)
    private String passwordConfirmation;
}
