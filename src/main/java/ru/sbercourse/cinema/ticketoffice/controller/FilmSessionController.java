package ru.sbercourse.cinema.ticketoffice.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercourse.cinema.ticketoffice.model.FilmSession;
import ru.sbercourse.cinema.ticketoffice.repository.FilmSessionRepository;

@RestController
@RequestMapping("/filmSessions")
@Tag(name = "Киносеансы", description = "Контроллер для работы с сеансами фильмов")
public class FilmSessionController extends GenericController<FilmSession> {

    public FilmSessionController(FilmSessionRepository filmSessionRepository) {
        setRepository(filmSessionRepository);
    }
}
