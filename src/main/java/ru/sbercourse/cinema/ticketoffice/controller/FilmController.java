package ru.sbercourse.cinema.ticketoffice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sbercourse.cinema.ticketoffice.dto.FilmDto;
import ru.sbercourse.cinema.ticketoffice.model.Film;
import ru.sbercourse.cinema.ticketoffice.model.FilmSession;
import ru.sbercourse.cinema.ticketoffice.repository.FilmSessionRepository;
import ru.sbercourse.cinema.ticketoffice.service.FilmService;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/films")
@Tag(name = "Фильмы", description = "Контроллер для работы с фильмами в кинотеатре")
public class FilmController extends GenericController<Film, FilmDto> {
    private FilmSessionRepository filmSessionRepository;


    public FilmController(FilmService filmService) {
        setService(filmService);
    }


    @Operation(summary = "Добавить создателя к фильму",
            description = "Позволяет добавить создателя к фильму по указанным идентификаторам")
    @PutMapping(value = "/{filmId}/addCreator/{creatorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FilmDto> addCreator(
            @PathVariable(value = "filmId") @Parameter(description = "Идентификатор фильма") Long filmId,
            @PathVariable(value = "creatorId") @Parameter(description = "Идентификатор создателя") Long creatorId) {
        FilmDto updatedFilmDto = ((FilmService) service).addCreator(filmId, creatorId);
        if (updatedFilmDto != null) {
            return ResponseEntity.ok(updatedFilmDto);
        } else return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Получить все актуальные фильмы",
            description = "Позволяет получить список всех фильмов с актуальными киносеансами")
    @GetMapping(value = "/actual", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<FilmDto>> getActual() {
        Set<Film> films = filmSessionRepository.getActual().stream()
                .map(FilmSession::getFilm).collect(Collectors.toSet());
        return films.isEmpty()
        ? ResponseEntity.notFound().build()
        : ResponseEntity.ok(service.getMapper().toDtos(films));
    }

    @Operation(summary = "Получить фильмы с киносеансами за период",
            description = "Позволяет получить список фильмов с киносеансами в указанный период")
    @GetMapping(value = "/actual/{start},{end}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<FilmDto>> getByDatePeriod(
            @PathVariable(value = "start") @Parameter(description = "Начало периода") LocalDateTime start,
            @PathVariable(value = "end") @Parameter(description = "Конец периода") LocalDateTime end) {
        Set<Film> films = filmSessionRepository.getByDatePeriod(start, end).stream()
                .map(FilmSession::getFilm).collect(Collectors.toSet());
        return films.isEmpty()
        ? ResponseEntity.notFound().build()
        : ResponseEntity.ok(service.getMapper().toDtos(films));
    }



    @Autowired
    public void setFilmSessionRepository(FilmSessionRepository filmSessionRepository) {
        this.filmSessionRepository = filmSessionRepository;
    }
}
