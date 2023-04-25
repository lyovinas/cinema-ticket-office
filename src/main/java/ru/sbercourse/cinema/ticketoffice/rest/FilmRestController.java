package ru.sbercourse.cinema.ticketoffice.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sbercourse.cinema.ticketoffice.dto.FilmDTO;
import ru.sbercourse.cinema.ticketoffice.model.Film;
import ru.sbercourse.cinema.ticketoffice.service.FilmService;

import java.util.List;

@RestController
@RequestMapping("/films")
@Tag(name = "Фильмы", description = "Контроллер для работы с фильмами в кинотеатре")
public class FilmRestController extends GenericRestController<Film, FilmDTO> {

    public FilmRestController(FilmService filmService) {
        service = filmService;
    }



    @Operation(summary = "Добавить создателя к фильму",
            description = "Позволяет добавить создателя к фильму по указанным идентификаторам")
    @PutMapping(value = "/{filmId}/addFilmCreator/{filmCreatorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FilmDTO> addFilmCreator(
            @PathVariable(value = "filmId") @Parameter(description = "Идентификатор фильма") Long filmId,
            @PathVariable(value = "filmCreatorId") @Parameter(description = "Идентификатор создателя") Long filmCreatorId) {
        FilmDTO updatedFilmDto = ((FilmService) service).addFilmCreator(filmId, filmCreatorId);
        if (updatedFilmDto != null) {
            return ResponseEntity.ok(updatedFilmDto);
        } else return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Получить все актуальные фильмы",
            description = "Позволяет получить список всех фильмов с киносеансами в будущем")
    @GetMapping(value = "/getAllAvailable", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FilmDTO>> getAllAvailable() {
        List<FilmDTO> availableFilms = ((FilmService) service).getAllAvailable();
        return availableFilms.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(availableFilms);
    }
}

//    @Operation(summary = "Получить все актуальные фильмы",
//            description = "Позволяет получить список всех фильмов, имеющих актуальные киносеансы")
//    @GetMapping(value = "/actual", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<FilmDTO>> getActual() {
//        List<Film> films = filmSessionService.getActual().stream()
//                .map(FilmSession::getFilm).toList();
//        return films.isEmpty()
//        ? ResponseEntity.notFound().build()
//        : ResponseEntity.ok(service.getMapper().toDTOs(films));
//    }

//    @Operation(summary = "Получить фильмы с киносеансами за период",
//            description = "Позволяет получить список фильмов с киносеансами в указанный период")
//    @GetMapping(value = "/getByPeriod/{start},{end}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<FilmExtendedDTO>> getByPeriod(
//            @PathVariable(value = "start") @Parameter(description = "Начало периода") LocalDate start,
//            @PathVariable(value = "end") @Parameter(description = "Конец периода") LocalDate end) {
//        List<Film> films = filmSessionService.getByPeriod(start, end).stream()
//                .map(FilmSession::getFilm).toList();
//        return films.isEmpty()
//        ? ResponseEntity.notFound().build()
//        : ResponseEntity.ok(filmExtendedMapper.toDTOs(films));
//    }
