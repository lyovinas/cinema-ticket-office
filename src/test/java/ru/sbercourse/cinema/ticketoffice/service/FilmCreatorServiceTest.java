package ru.sbercourse.cinema.ticketoffice.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.sbercourse.cinema.ticketoffice.dto.FilmCreatorDTO;
import ru.sbercourse.cinema.ticketoffice.mapper.FilmCreatorMapper;
import ru.sbercourse.cinema.ticketoffice.model.FilmCreator;
import ru.sbercourse.cinema.ticketoffice.repository.FilmCreatorRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class FilmCreatorServiceTest extends GenericServiceTest<FilmCreator, FilmCreatorDTO> {

    public FilmCreatorServiceTest() {
        super();
        repository = Mockito.mock(FilmCreatorRepository.class);
        mapper = Mockito.mock(FilmCreatorMapper.class);
        service = new FilmCreatorService((FilmCreatorRepository) repository, (FilmCreatorMapper) mapper);
    }



    @Test
    void searchFilmCreators() {
    }

    @Test
    void getAll() {
//        Mockito.when(mapper.toEntity(FilmCreatorTestData.FILM_CREATOR_DTO_1)).thenReturn(FilmCreatorTestData.FILM_CREATOR_1);
//        Mockito.when(mapper.toDTO(FilmCreatorTestData.FILM_CREATOR_1)).thenReturn(FilmCreatorTestData.FILM_CREATOR_DTO_1);
        Mockito.when(repository.findAll()).thenReturn(FilmCreatorTestData.FILM_CREATOR_LIST);
        Mockito.when(mapper.toDTOs(FilmCreatorTestData.FILM_CREATOR_LIST)).thenReturn(FilmCreatorTestData.FILM_CREATOR_DTO_LIST);
        List<FilmCreatorDTO> filmCreatorDTOList = service.getAll();
        log.info("Testing getAll(): " + filmCreatorDTOList);
        assertEquals(FilmCreatorTestData.FILM_CREATOR_DTO_LIST, filmCreatorDTOList);
    }

    @Test
    void getById() {

    }

    @Test
    void create() {

    }

    @Test
    void update() {

    }

    @Test
    void delete() {

    }

    @Test
    void softDelete() {

    }

    @Test
    void restore() {

    }
}
