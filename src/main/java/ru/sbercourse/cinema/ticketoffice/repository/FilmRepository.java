package ru.sbercourse.cinema.ticketoffice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sbercourse.cinema.ticketoffice.model.Film;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface FilmRepository extends GenericRepository<Film> {

    @Query(nativeQuery = true,
            value = """
                    select distinct f.*
                    from films f
                    left join films_film_creators ffc on f.id = ffc.film_id
                    join film_creators fc on fc.id = ffc.film_creator_id
                    where f.title ilike '%' || coalesce(:filmTitle, '%') || '%'
                    and cast(f.genre as varchar(2)) like coalesce(:genre,'%')
                    and fc.full_name ilike '%' || coalesce(:fio, '%') || '%'
            """)
    Page<Film> searchFilms(@Param(value = "genre") String genre,
                           @Param(value = "filmTitle") String filmTitle,
                           @Param(value = "fio") String filmCreatorFullName,
                           PageRequest pageRequest);

    @Query(nativeQuery = true,
            value = """
                    select distinct f.*
                    from films f
                    join film_sessions fs on f.id = fs.film_id
                                        and (fs.start_date > current_date or (fs.start_date = current_date and fs.start_time >= :startTime))
                                        and f.is_deleted = false
                                        and fs.is_deleted = false
            """)
    List<Film> getAllAvailable(@Param(value = "startTime") LocalTime startTime);

    @Query(nativeQuery = true,
            value = """
                    select distinct f.*
                    from films f
                    join film_sessions fs on f.id = fs.film_id
                                        and fs.start_date = :startDate
                                        and fs.start_time >= :startTime
                                        and f.is_deleted = false
                                        and fs.is_deleted = false
            """)
    List<Film> getAllByDate(@Param(value = "startDate") LocalDate filmSessionStartDate, @Param(value = "startTime") LocalTime filmSessionStartTime);
}
