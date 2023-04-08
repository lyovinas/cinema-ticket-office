package ru.sbercourse.cinema.ticketoffice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sbercourse.cinema.ticketoffice.model.FilmSession;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface FilmSessionRepository extends GenericRepository<FilmSession> {

    @Query(value = "select * from film_sessions where start between ?1 and ?2", nativeQuery = true)
    Set<FilmSession> getByPeriod(LocalDateTime start, LocalDateTime end);

    @Query(value = "select * from film_sessions where start >= now()", nativeQuery = true)
    Set<FilmSession> getActual();
}
