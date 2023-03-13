package ru.sbercourse.cinema.ticketoffice.controller;

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
import ru.sbercourse.cinema.ticketoffice.dto.CreatorDto;
import ru.sbercourse.cinema.ticketoffice.dto.FilmSessionDto;
import ru.sbercourse.cinema.ticketoffice.model.Creator;
import ru.sbercourse.cinema.ticketoffice.model.Film;
import ru.sbercourse.cinema.ticketoffice.model.FilmSession;
import ru.sbercourse.cinema.ticketoffice.repository.FilmRepository;
import ru.sbercourse.cinema.ticketoffice.repository.FilmSessionRepository;
import ru.sbercourse.cinema.ticketoffice.service.FilmSessionService;

import java.util.Set;

@RestController
@RequestMapping("/filmSessions")
@Tag(name = "Киносеансы", description = "Контроллер для работы с сеансами фильмов")
public class FilmSessionController extends GenericController<FilmSession, FilmSessionDto> {
//    private FilmRepository filmRepository;


    public FilmSessionController(FilmSessionService filmSessionService) {
        setService(filmSessionService);
    }


//    @Operation(summary = "Получить актуальные киносеансы",
//            description = "Позволяет получить список актуальных киносеансов")
//    @GetMapping(value = "/actual", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Set<FilmSessionDto>> getActual() {
//        Set<FilmSession> filmSessions = ((FilmSessionRepository) service.getRepository()).getActual();
//        return filmSessions != null
//                ? ResponseEntity.ok(service.getMapper().toDtos(filmSessions))
//                : ResponseEntity.notFound().build();
//    }

//    @Operation(summary = "Получить киносеансы по ID фильма",
//            description = "Позволяет получить список киносеансов по заданному ID фильма")
//    @GetMapping(value = "/film/{filmId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Set<FilmSessionDto>> getByFilmId(
//            @PathVariable("filmId") @Parameter(description = "Идентификатор фильма") Long filmId) {
//        Film film = filmRepository.findById(filmId).orElse(null);
//        if (film != null) {
//            Set<FilmSession> filmSessions = film.getFilmSessions();
//            if (filmSessions != null) {
//                return ResponseEntity.ok(service.getMapper().toDtos(filmSessions));
//            }
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//
//    @Autowired
//    public void setFilmRepository(FilmRepository filmRepository) {
//        this.filmRepository = filmRepository;
//    }
}
