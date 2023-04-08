package ru.sbercourse.cinema.ticketoffice.REST;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercourse.cinema.ticketoffice.dto.FilmCreatorDTO;
import ru.sbercourse.cinema.ticketoffice.model.FilmCreator;
import ru.sbercourse.cinema.ticketoffice.service.FilmCreatorService;
import ru.sbercourse.cinema.ticketoffice.service.FilmService;

import java.util.List;

@RestController
@RequestMapping("/filmCreators")
@Tag(name = "Создатели фильмов", description = "Контроллер для работы с создателями фильмов")
public class FilmCreatorRestController extends GenericRestController<FilmCreator, FilmCreatorDTO> {

    private FilmService filmService;


    public FilmCreatorRestController(FilmCreatorService filmCreatorService) {
        service = filmCreatorService;
    }


    // TODO проверить необходимость, вместо получения фильма вместе с создателями
    @Operation(summary = "Получить создателей по ID фильма",
            description = "Позволяет получить список создателей по заданному ID фильма")
    @GetMapping(value = "/film/{filmId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FilmCreatorDTO>> getByFilmId(
            @PathVariable("filmId") @Parameter(description = "Идентификатор фильма") Long filmId) {
        if (filmId != null) {
            List<FilmCreator> filmCreators = filmService.getCreators(filmId);
            if (filmCreators != null) {
                return ResponseEntity.ok(service.getMapper().toDTOs(filmCreators));
            }
        }
        return ResponseEntity.notFound().build();
    }


    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }
}
