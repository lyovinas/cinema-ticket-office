package ru.sbercourse.cinema.ticketoffice.service;

import ru.sbercourse.cinema.ticketoffice.dto.FilmCreatorDTO;
import ru.sbercourse.cinema.ticketoffice.model.FilmCreator;

import java.util.List;

public interface FilmCreatorTestData {

    FilmCreator FILM_CREATOR_1 = new FilmCreator("Full Name 1", "Position 1");
    FilmCreator FILM_CREATOR_2 = new FilmCreator("Full Name 2", "Position 2");
    FilmCreator FILM_CREATOR_3 = new FilmCreator("Full Name 3", "Position 3");
    List<FilmCreator> FILM_CREATOR_LIST = List.of(FILM_CREATOR_1, FILM_CREATOR_2, FILM_CREATOR_3);

    FilmCreatorDTO FILM_CREATOR_DTO_1 = new FilmCreatorDTO("Full Name 1", "Position 1");
    FilmCreatorDTO FILM_CREATOR_DTO_2 = new FilmCreatorDTO("Full Name 2", "Position 2");
    FilmCreatorDTO FILM_CREATOR_DTO_3 = new FilmCreatorDTO("Full Name 3", "Position 3");
    List<FilmCreatorDTO> FILM_CREATOR_DTO_LIST = List.of(FILM_CREATOR_DTO_1, FILM_CREATOR_DTO_2, FILM_CREATOR_DTO_3);
}
