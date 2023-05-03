package ru.sbercourse.cinema.ticketoffice.repository;

import org.springframework.stereotype.Repository;
import ru.sbercourse.cinema.ticketoffice.model.FilmSession;

@Repository
public interface FilmSessionRepository extends GenericRepository<FilmSession> {
}
