package ru.sbercourse.cinema.ticketoffice.repository;

import org.springframework.stereotype.Repository;
import ru.sbercourse.cinema.ticketoffice.model.FilmCreator;

import java.util.List;

@Repository
public interface FilmCreatorRepository extends GenericRepository<FilmCreator> {

    List<FilmCreator> getAllByFullNameContainsIgnoreCase(String fullName);
}
