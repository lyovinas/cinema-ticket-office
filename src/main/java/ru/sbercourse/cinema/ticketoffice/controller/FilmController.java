package ru.sbercourse.cinema.ticketoffice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercourse.cinema.ticketoffice.model.Creator;
import ru.sbercourse.cinema.ticketoffice.model.Film;
import ru.sbercourse.cinema.ticketoffice.repository.CreatorRepository;
import ru.sbercourse.cinema.ticketoffice.repository.FilmRepository;

@RestController
@RequestMapping("/films")
@Tag(name = "Фильмы", description = "Контроллер для работы с фильмами в кинотеатре")
public class FilmController extends GenericController<Film> {
    private FilmRepository filmRepository;
    private CreatorRepository creatorRepository;


    public FilmController(FilmRepository filmRepository) {
        setRepository(filmRepository);
    }


    @Operation(summary = "Добавить создателя к фильму",
            description = "Позволяет добавить создателя к фильму по указанным идентификаторам")
    @PutMapping(value = "/{filmId}/addCreator/{creatorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Film> addCreator(
            @PathVariable(value = "filmId") @Parameter(description = "Идентификатор фильма") Long filmId,
            @PathVariable(value = "creatorId") @Parameter(description = "Идентификатор создателя") Long creatorId) {
        Film film = filmRepository.findById(filmId).orElse(null);
        Creator creator = creatorRepository.findById(creatorId).orElse(null);
        if (film != null && creator != null) {
            film.getCreators().add(creator);
            filmRepository.save(film);
            return ResponseEntity.ok(film);
        } else return ResponseEntity.notFound().build();
    }


    @Autowired
    public void setFilmRepository(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Autowired
    public void setDirectorRepository(CreatorRepository creatorRepository) {
        this.creatorRepository = creatorRepository;
    }
}
