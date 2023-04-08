package ru.sbercourse.cinema.ticketoffice.mapper;

import org.springframework.stereotype.Component;
import ru.sbercourse.cinema.ticketoffice.dto.FilmWithSessionsDTO;
import ru.sbercourse.cinema.ticketoffice.model.Film;

@Component
public class FilmWithSessionsMapper extends GenericMapper<Film, FilmWithSessionsDTO> {

    public FilmWithSessionsMapper() {
        super(Film.class, FilmWithSessionsDTO.class);
    }
}
