package ru.sbercourse.cinema.ticketoffice.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercourse.cinema.ticketoffice.model.Role;
import ru.sbercourse.cinema.ticketoffice.repository.RoleRepository;

@RestController
@RequestMapping("/roles")
@Tag(name = "Роли", description = "Контроллер для работы с ролями пользователей сервиса")
public class RoleController extends GenericController<Role>{

    public RoleController(RoleRepository roleRepository) {
        setRepository(roleRepository);
    }
}
