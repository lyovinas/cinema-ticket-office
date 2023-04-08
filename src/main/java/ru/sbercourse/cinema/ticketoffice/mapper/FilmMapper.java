package ru.sbercourse.cinema.ticketoffice.mapper;

import org.springframework.stereotype.Component;
import ru.sbercourse.cinema.ticketoffice.dto.FilmDTO;
import ru.sbercourse.cinema.ticketoffice.model.Film;

@Component
public class FilmMapper extends GenericMapper<Film, FilmDTO> {

    public FilmMapper() {
        super(Film.class, FilmDTO.class);
    }

}
