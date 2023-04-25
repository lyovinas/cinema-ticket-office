package ru.sbercourse.cinema.ticketoffice.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercourse.cinema.ticketoffice.dto.FilmCreatorDTO;
import ru.sbercourse.cinema.ticketoffice.model.FilmCreator;
import ru.sbercourse.cinema.ticketoffice.service.FilmCreatorService;

@RestController
@RequestMapping("/filmCreators")
@Tag(name = "Создатели фильмов", description = "Контроллер для работы с создателями фильмов")
public class FilmCreatorRestController extends GenericRestController<FilmCreator, FilmCreatorDTO> {

    public FilmCreatorRestController(FilmCreatorService filmCreatorService) {
        service = filmCreatorService;
    }

}
