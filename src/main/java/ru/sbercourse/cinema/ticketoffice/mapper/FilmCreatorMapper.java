package ru.sbercourse.cinema.ticketoffice.mapper;

import org.springframework.stereotype.Component;
import ru.sbercourse.cinema.ticketoffice.dto.FilmCreatorDTO;
import ru.sbercourse.cinema.ticketoffice.model.FilmCreator;

@Component
public class FilmCreatorMapper extends GenericMapper<FilmCreator, FilmCreatorDTO> {

    public FilmCreatorMapper() {
        super(FilmCreator.class, FilmCreatorDTO.class);
    }

}
