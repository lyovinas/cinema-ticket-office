package ru.sbercourse.cinema.ticketoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sbercourse.cinema.ticketoffice.model.Role;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO пользователя сервиса")
public class UserDTO extends GenericDTO {

    @Schema(description = "Имя", example = "Иван")
    private String firstName;

    @Schema(description = "Фамилия", example = "Иванов")
    private String lastName;

    @Schema(description = "Дата рождения")
    private LocalDate birthDate;

    @Schema(description = "Адрес электронной почты", example = "name@domain.com")
    private String email;

    @Schema(description = "Логин на сервисе", example = "SomeLogin")
    private String login;

    @Schema(description = "Пароль для входа", example = "P@ssw0rd")
    private String password;

    @Schema(description = "Токен для смены пароля")
    private String changePasswordToken;

    @Schema(description = "Роль на сервисе", accessMode = Schema.AccessMode.READ_ONLY)
    private Role role;

    @Schema(description = "Идентификаторы заказов", accessMode = Schema.AccessMode.READ_ONLY)
    private Set<Long> ordersIds;
}

//    @Schema(description = "Отзывы пользователя", accessMode = Schema.AccessMode.READ_ONLY)
//    private Set<ReviewDTO> reviews;
