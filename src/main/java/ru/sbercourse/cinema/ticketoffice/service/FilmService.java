package ru.sbercourse.cinema.ticketoffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbercourse.cinema.ticketoffice.dto.FilmDto;
import ru.sbercourse.cinema.ticketoffice.mapper.FilmMapper;
import ru.sbercourse.cinema.ticketoffice.model.Creator;
import ru.sbercourse.cinema.ticketoffice.model.Film;
import ru.sbercourse.cinema.ticketoffice.repository.CreatorRepository;
import ru.sbercourse.cinema.ticketoffice.repository.FilmRepository;

@Service
public class FilmService extends GenericService<Film, FilmDto> {

    private CreatorRepository creatorRepository;


    public FilmService(FilmRepository filmRepository, FilmMapper filmMapper) {
        setRepository(filmRepository);
        setMapper(filmMapper);
    }


    public FilmDto addCreator(Long filmId, Long creatorId) {
        Film film = repository.findById(filmId).orElse(null);
        Creator creator = creatorRepository.findById(creatorId).orElse(null);
        if (film != null && creator != null) {
            film.getCreators().add(creator);
            repository.save(film);
            return mapper.toDto(film);
        } else return null;
    }


    @Autowired
    public void setCreatorRepository(CreatorRepository creatorRepository) {
        this.creatorRepository = creatorRepository;
    }
}
