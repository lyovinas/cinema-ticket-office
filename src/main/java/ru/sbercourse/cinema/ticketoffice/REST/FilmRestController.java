package ru.sbercourse.cinema.ticketoffice.REST;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sbercourse.cinema.ticketoffice.dto.FilmDTO;
import ru.sbercourse.cinema.ticketoffice.dto.FilmWithSessionsDTO;
import ru.sbercourse.cinema.ticketoffice.mapper.FilmWithSessionsMapper;
import ru.sbercourse.cinema.ticketoffice.model.Film;
import ru.sbercourse.cinema.ticketoffice.model.FilmSession;
import ru.sbercourse.cinema.ticketoffice.service.FilmService;
import ru.sbercourse.cinema.ticketoffice.service.FilmSessionService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/films")
@Tag(name = "Фильмы", description = "Контроллер для работы с фильмами в кинотеатре")
public class FilmRestController extends GenericRestController<Film, FilmDTO> {
    private FilmSessionService filmSessionService;
    private FilmWithSessionsMapper filmWithSessionsMapper;



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
            description = "Позволяет получить список всех фильмов, имеющих актуальные киносеансы")
    @GetMapping(value = "/actual", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FilmDTO>> getActual() {
        List<Film> films = filmSessionService.getActual().stream()
                .map(FilmSession::getFilm).toList();
        return films.isEmpty()
        ? ResponseEntity.notFound().build()
        : ResponseEntity.ok(service.getMapper().toDTOs(films));
    }

    @Operation(summary = "Получить фильмы с киносеансами за период",
            description = "Позволяет получить список фильмов с киносеансами в указанный период")
    @GetMapping(value = "/getByPeriod/{start},{end}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FilmWithSessionsDTO>> getByPeriod(
            @PathVariable(value = "start") @Parameter(description = "Начало периода") LocalDateTime start,
            @PathVariable(value = "end") @Parameter(description = "Конец периода") LocalDateTime end) {
        List<Film> films = filmSessionService.getByPeriod(start, end).stream()
                .map(FilmSession::getFilm).toList();
        return films.isEmpty()
        ? ResponseEntity.notFound().build()
        : ResponseEntity.ok(filmWithSessionsMapper.toDTOs(films));
    }

    @Operation(summary = "Получить все фильмы (с киносеансами)",
            description = "Позволяет получить список всех фильмов вместе с киносеансами")
    @GetMapping(value = "/withSessions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FilmWithSessionsDTO>> getAllWithSessions() {
        List<FilmWithSessionsDTO> filmsWithSessions = ((FilmService) service).getAllWithSessions();
        return filmsWithSessions.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(filmsWithSessions);
    }



    @Autowired
    public void setFilmSessionService(FilmSessionService filmSessionService) {
        this.filmSessionService = filmSessionService;
    }

    @Autowired
    public void setFilmWithSessionsMapper(FilmWithSessionsMapper filmWithSessionsMapper) {
        this.filmWithSessionsMapper = filmWithSessionsMapper;
    }
}
