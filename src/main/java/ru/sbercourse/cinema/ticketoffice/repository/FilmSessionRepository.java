package ru.sbercourse.cinema.ticketoffice.repository;

import org.springframework.stereotype.Repository;
import ru.sbercourse.cinema.ticketoffice.model.FilmSession;

@Repository
public interface FilmSessionRepository extends GenericRepository<FilmSession> {

//    @Query(value = "select * from film_sessions where start_date between ?1 and ?2", nativeQuery = true)
//    Set<FilmSession> getByPeriod(LocalDate start, LocalDate end);

//    @Query(value = "select * from film_sessions where start >= now()", nativeQuery = true)
//    Set<FilmSession> getActual();
}
