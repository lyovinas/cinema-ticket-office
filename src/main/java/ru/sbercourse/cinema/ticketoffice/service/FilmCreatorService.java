package ru.sbercourse.cinema.ticketoffice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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



    public Page<FilmCreatorDTO> searchFilmCreators(final String fullName, Pageable pageable) {
        Page<FilmCreator> page = ((FilmCreatorRepository) repository)
                .getAllByFullNameContainsIgnoreCaseAndIsDeletedFalse(fullName, pageable);
        List<FilmCreatorDTO> result = mapper.toDTOs(page.getContent());
        return new PageImpl<>(result, pageable, page.getTotalElements());
    }
}
