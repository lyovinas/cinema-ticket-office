package ru.sbercourse.cinema.ticketoffice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.sbercourse.cinema.ticketoffice.model.FilmCreator;

@Repository
public interface FilmCreatorRepository extends GenericRepository<FilmCreator> {

    Page<FilmCreator> getAllByFullNameContainsIgnoreCaseAndIsDeletedFalse(String fullName, Pageable pageable);
}
