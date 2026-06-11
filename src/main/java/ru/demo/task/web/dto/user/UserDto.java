package ru.demo.task.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.demo.task.web.dto.validation.OnCreate;
import ru.demo.task.web.dto.validation.OnUpdate;

@Data
@Schema(description = "User DTO")
public class UserDto {
    @Schema(name = "User id", example = "1")
    @NotNull(message = "Id must be not null.", groups = OnUpdate.class)
    private Long id;

    @Schema(name = "User name", example = "John Doe")
    @NotNull(message = "Name must be not null.", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Name length must be smaller than 255 symbols.")
    private String name;

    @Schema(name = "User email", example = "Johndoe@mail.ru")
    @NotNull(message = "Username must be not null.", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Username length must be smaller than 255 symbols.")
    private String username;

    @Schema(name = "User password", example = "$2a$10$MZagFd5.nZAqUySu7QQRXey7DblYyr2G0xnFk.tYcbuScZgWvVjw6")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password must be not null.", groups = {OnUpdate.class, OnCreate.class})
    private String password;

    @Schema(name = "User password confirmation", example = "$2a$10$MZagFd5.nZAqUySu7QQRXey7DblYyr2G0xnFk.tYcbuScZgWvVjw6")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password confirmation must be not null.", groups = OnUpdate.class)
    private String passwordConfirmation;
}
