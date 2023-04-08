package ru.sbercourse.cinema.ticketoffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.sbercourse.cinema.ticketoffice.dto.FilmDTO;
import ru.sbercourse.cinema.ticketoffice.dto.FilmSearchDTO;
import ru.sbercourse.cinema.ticketoffice.dto.FilmWithSessionsDTO;
import ru.sbercourse.cinema.ticketoffice.mapper.FilmMapper;
import ru.sbercourse.cinema.ticketoffice.mapper.FilmWithSessionsMapper;
import ru.sbercourse.cinema.ticketoffice.model.Film;
import ru.sbercourse.cinema.ticketoffice.model.FilmCreator;
import ru.sbercourse.cinema.ticketoffice.repository.FilmCreatorRepository;
import ru.sbercourse.cinema.ticketoffice.repository.FilmRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.sbercourse.cinema.ticketoffice.constants.UserRolesConstants.ADMIN;

@Service
public class FilmService extends GenericService<Film, FilmDTO> {

    private FilmCreatorRepository filmCreatorRepository;
    private FilmWithSessionsMapper filmWithSessionsMapper;



    public FilmService(FilmRepository filmRepository, FilmMapper filmMapper) {
        repository = filmRepository;
        mapper = filmMapper;
    }



    public FilmDTO addFilmCreator(Long filmId, Long filmCreatorId) {
        Film film = repository.findById(filmId).orElse(null);
        FilmCreator filmCreator = filmCreatorRepository.findById(filmCreatorId).orElse(null);
        if (film != null && filmCreator != null) {
            film.getFilmCreators().add(filmCreator);
            repository.save(film);
            return mapper.toDTO(film);
        } else return null;
    }

    public void deleteFilmCreator(Long filmId, Long filmCreatorId) {
        Film film = repository.findById(filmId).orElse(null);
        FilmCreator filmCreator = filmCreatorRepository.findById(filmCreatorId).orElse(null);
        if (film != null && filmCreator != null) {
            film.getFilmCreators().remove(filmCreator);
            repository.save(film);
        }
    }

    public List<FilmCreator> getCreators(Long id) { //TODO убрать, если не используется (в FilmCreatorController)
        Film film = repository.findById(id).orElse(null);
        return film != null
                ? film.getFilmCreators().stream().toList()
                : null;
    }

    public List<FilmWithSessionsDTO> getAllWithSessions() {
        return filmWithSessionsMapper.toDTOs(repository.findAll());
    }

    public FilmWithSessionsDTO getWithSessionsById(Long id) {
        return filmWithSessionsMapper.toDTO(repository.findById(id).orElse(null));
    }

    @Override
    public FilmDTO update(Long id, FilmDTO filmDTO) {
        Film film = mapper.toEntity(filmDTO);

        if (id != null) {
            Film existingFilm = repository.findById(id).orElse(null);
            if (existingFilm != null) {
                if (film.getFilmCreators() == null) {
                    film.setFilmCreators(existingFilm.getFilmCreators());
                }
                return super.update(id, mapper.toDTO(film));
            }
        }
        return null;
    }

    public Object findFilms(FilmSearchDTO filmSearchDTO, PageRequest pageRequest) {
        String genre = filmSearchDTO.getGenre() != null ? String.valueOf(filmSearchDTO.getGenre().ordinal()) : "%";
        Page<Film> filmPage = ((FilmRepository) repository).searchFilms(genre, filmSearchDTO.getFilmTitle(),
                filmSearchDTO.getFilmCreatorFullName(), pageRequest);
        List<FilmWithSessionsDTO> result = filmWithSessionsMapper.toDTOs(filmPage.getContent());
        return new PageImpl<>(result, pageRequest, filmPage.getTotalElements());
    }



    @Autowired
    public void setFilmCreatorRepository(FilmCreatorRepository filmCreatorRepository) {
        this.filmCreatorRepository = filmCreatorRepository;
    }

    @Autowired
    public void setFilmWithSessionsMapper(FilmWithSessionsMapper filmWithSessionsMapper) {
        this.filmWithSessionsMapper = filmWithSessionsMapper;
    }
}
