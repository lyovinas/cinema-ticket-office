package ru.sbercourse.cinema.ticketoffice.service;

import org.springframework.stereotype.Service;
import ru.sbercourse.cinema.ticketoffice.dto.FilmSessionDTO;
import ru.sbercourse.cinema.ticketoffice.mapper.FilmSessionMapper;
import ru.sbercourse.cinema.ticketoffice.model.FilmSession;
import ru.sbercourse.cinema.ticketoffice.repository.FilmSessionRepository;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class FilmSessionService extends GenericService<FilmSession, FilmSessionDTO> {

    public FilmSessionService(FilmSessionRepository filmSessionRepository, FilmSessionMapper filmSessionMapper) {
        repository = filmSessionRepository;
        mapper = filmSessionMapper;
    }


    public Set<FilmSession> getByPeriod(LocalDateTime start, LocalDateTime end) {
        return start !=null && end != null
                ? ((FilmSessionRepository) repository).getByPeriod(start, end)
                : null;
    }

    public Set<FilmSession> getActual() {
        return ((FilmSessionRepository) repository).getActual();
    }
}
