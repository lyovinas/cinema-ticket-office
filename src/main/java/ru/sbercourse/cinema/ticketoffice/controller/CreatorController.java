package ru.sbercourse.cinema.ticketoffice.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercourse.cinema.ticketoffice.model.Creator;
import ru.sbercourse.cinema.ticketoffice.repository.CreatorRepository;

@RestController
@RequestMapping("/creators")
@Tag(name = "Создатели", description = "Контроллер для работы с создателями фильмов")
public class CreatorController extends GenericController<Creator> {

    public CreatorController(CreatorRepository creatorRepository) {
        setRepository(creatorRepository);
    }
}
