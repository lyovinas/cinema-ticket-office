package ru.sbercourse.cinema.ticketoffice.service;

import org.springframework.stereotype.Service;
import ru.sbercourse.cinema.ticketoffice.dto.FilmSessionDto;
import ru.sbercourse.cinema.ticketoffice.mapper.FilmSessionMapper;
import ru.sbercourse.cinema.ticketoffice.model.FilmSession;
import ru.sbercourse.cinema.ticketoffice.repository.FilmSessionRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class FilmSessionService extends GenericService<FilmSession, FilmSessionDto> {

    public FilmSessionService(FilmSessionRepository filmSessionRepository, FilmSessionMapper filmSessionMapper) {
        setRepository(filmSessionRepository);
        setMapper(filmSessionMapper);
    }


//    public Set<FilmSession> getAllByIds(Set<Long> ids) {
//        return new HashSet<>(repository.findAllById(ids));
//    }
}
