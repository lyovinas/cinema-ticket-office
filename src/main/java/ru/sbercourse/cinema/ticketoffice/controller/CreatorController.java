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
import ru.sbercourse.cinema.ticketoffice.model.Creator;
import ru.sbercourse.cinema.ticketoffice.model.Film;
import ru.sbercourse.cinema.ticketoffice.repository.FilmRepository;
import ru.sbercourse.cinema.ticketoffice.service.CreatorService;

import java.util.Set;

@RestController
@RequestMapping("/creators")
@Tag(name = "Создатели фильмов", description = "Контроллер для работы с создателями фильмов")
public class CreatorController extends GenericController<Creator, CreatorDto> {

//    private FilmRepository filmRepository;


    public CreatorController(CreatorService creatorService) {
        setService(creatorService);
    }


//    @Operation(summary = "Получить создателей по ID фильма",
//            description = "Позволяет получить список создателей по заданному ID фильма")
//    @GetMapping(value = "/film/{filmId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Set<CreatorDto>> getByFilmId(
//            @PathVariable("filmId") @Parameter(description = "Идентификатор фильма") Long filmId) {
//        Film film = filmRepository.findById(filmId).orElse(null);
//        if (film != null) {
//            Set<Creator> creators = film.getCreators();
//            if (creators != null) {
//                return ResponseEntity.ok(service.getMapper().toDtos(creators));
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
