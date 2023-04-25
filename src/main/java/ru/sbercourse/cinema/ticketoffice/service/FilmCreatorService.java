package ru.sbercourse.cinema.ticketoffice.service;

import org.springframework.stereotype.Service;
import ru.sbercourse.cinema.ticketoffice.dto.FilmCreatorDTO;
import ru.sbercourse.cinema.ticketoffice.mapper.FilmCreatorMapper;
import ru.sbercourse.cinema.ticketoffice.model.FilmCreator;
import ru.sbercourse.cinema.ticketoffice.repository.FilmCreatorRepository;

import java.util.List;

@Service
public class FilmCreatorService extends GenericService<FilmCreator, FilmCreatorDTO> {

    public FilmCreatorService(FilmCreatorRepository filmCreatorRepository, FilmCreatorMapper filmCreatorMapper) {
        repository = filmCreatorRepository;
        mapper = filmCreatorMapper;
    }



    public List<FilmCreatorDTO> searchFilmCreators(final String fullName) {
        List<FilmCreator> filmCreators = ((FilmCreatorRepository) repository)
                .getAllByFullNameContainsIgnoreCase(fullName);
        return mapper.toDTOs(filmCreators);
    }
}
