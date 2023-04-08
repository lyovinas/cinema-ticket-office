package ru.sbercourse.cinema.ticketoffice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sbercourse.cinema.ticketoffice.model.Film;

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
}
