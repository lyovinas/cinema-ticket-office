package ru.sbercourse.cinema.ticketoffice.REST;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercourse.cinema.ticketoffice.dto.UserDTO;
import ru.sbercourse.cinema.ticketoffice.model.User;
import ru.sbercourse.cinema.ticketoffice.service.UserService;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "Контроллер для работы с пользователями сервиса")
public class UserRestController extends GenericRestController<User, UserDTO> {

    public UserRestController(UserService userService) {
        service = userService;
    }
}
