package ru.sbercourse.cinema.ticketoffice.repository;

import org.springframework.stereotype.Repository;
import ru.sbercourse.cinema.ticketoffice.model.Film;

@Repository
public interface FilmRepository extends GenericRepository<Film> {
}
