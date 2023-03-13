package ru.sbercourse.cinema.ticketoffice.mapper;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbercourse.cinema.ticketoffice.dto.FilmDto;
import ru.sbercourse.cinema.ticketoffice.model.*;
import ru.sbercourse.cinema.ticketoffice.repository.CreatorRepository;
import ru.sbercourse.cinema.ticketoffice.repository.FilmSessionRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmMapper extends GenericMapper<Film, FilmDto>
        implements ConverterForSpecificFields<Film, FilmDto> {

    private CreatorRepository creatorRepository;
    private FilmSessionRepository filmSessionRepository;


    public FilmMapper() {
        super(Film.class, FilmDto.class);
    }


    @PostConstruct
    @Override
    public void setupMapper() {
        modelMapper.createTypeMap(Film.class, FilmDto.class)
                .addMappings(m -> {
                    m.skip(FilmDto::setGenreText);
                    m.skip(FilmDto::setCreatorsIds);
                    m.skip(FilmDto::setFilmSessionsIds);
                })
                .setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(FilmDto.class, Film.class)
                .addMappings(m -> {
                    m.skip(Film::setGenre);
                    m.skip(Film::setCreators);
                    m.skip(Film::setFilmSessions);
                })
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(FilmDto source, Film destination) {
        String genreText = source.getGenreText();
        if (genreText != null) {
            destination.setGenre(Genre.getByText(genreText));
        } else destination.setCreators(null);

        Set<Long> creatorsIds = source.getCreatorsIds();
        if (creatorsIds != null) {
            destination.setCreators(new HashSet<>(creatorRepository.findAllById(creatorsIds)));
        } else destination.setCreators(null);

        Set<Long> filmSessionsIds = source.getFilmSessionsIds();
        if (filmSessionsIds != null) {
            destination.setFilmSessions(new HashSet<>(filmSessionRepository.findAllById(filmSessionsIds)));
        } else destination.setFilmSessions(null);
    }

    @Override
    public void mapSpecificFields(Film source, FilmDto destination) {
        String genreText = null;
        Genre genre = source.getGenre();
        if (genre != null) {
            genreText = genre.getText();
        }
        destination.setGenreText(genreText);

        Set<Long> creatorsIds = null;
        Set<Creator> creators = source.getCreators();
        if (creators != null) {
            creatorsIds = creators.stream()
                    .map(GenericModel::getId)
                    .collect(Collectors.toSet());
        }
        destination.setCreatorsIds(creatorsIds);

        Set<Long> filmSessionsIds = null;
        Set<FilmSession> filmSessions = source.getFilmSessions();
        if (filmSessions != null) {
            filmSessionsIds = filmSessions.stream()
                    .map(GenericModel::getId)
                    .collect(Collectors.toSet());
        }
        destination.setFilmSessionsIds(filmSessionsIds);
    }


    @Autowired
    public void setCreatorRepository(CreatorRepository creatorRepository) {
        this.creatorRepository = creatorRepository;
    }

    @Autowired
    public void setFilmSessionRepository(FilmSessionRepository filmSessionRepository) {
        this.filmSessionRepository = filmSessionRepository;
    }
}
