package ru.sbercourse.cinema.ticketoffice.mapper;

import org.springframework.stereotype.Component;
import ru.sbercourse.cinema.ticketoffice.dto.FilmExtendedDTO;
import ru.sbercourse.cinema.ticketoffice.model.Film;

@Component
public class FilmExtendedMapper extends GenericMapper<Film, FilmExtendedDTO> {

    public FilmExtendedMapper() {
        super(Film.class, FilmExtendedDTO.class);
    }
}
