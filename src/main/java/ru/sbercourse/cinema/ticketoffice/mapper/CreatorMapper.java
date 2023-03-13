package ru.sbercourse.cinema.ticketoffice.mapper;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbercourse.cinema.ticketoffice.dto.CreatorDto;
import ru.sbercourse.cinema.ticketoffice.model.Creator;
import ru.sbercourse.cinema.ticketoffice.model.Film;
import ru.sbercourse.cinema.ticketoffice.model.GenericModel;
import ru.sbercourse.cinema.ticketoffice.repository.FilmRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CreatorMapper extends GenericMapper<Creator, CreatorDto> {
//        implements ConverterForSpecificFields<Creator, CreatorDto>{

//    private FilmRepository filmRepository;


    public CreatorMapper() {
        super(Creator.class, CreatorDto.class);
    }


//    @PostConstruct
//    @Override
//    public void setupMapper() {
//        modelMapper.createTypeMap(Creator.class, CreatorDto.class)
//                .addMappings(m -> m.skip(CreatorDto::setFilmsIds))
//                .setPostConverter(toDtoConverter());
//
//        modelMapper.createTypeMap(CreatorDto.class, Creator.class)
//                .addMappings(m -> m.skip(Creator::setFilms))
//                .setPostConverter(toEntityConverter());
//    }
//
//    @Override
//    public void mapSpecificFields(CreatorDto source, Creator destination) {
//        Set<Long> filmsIds = source.getFilmsIds();
//        if (filmsIds != null) {
//            destination.setFilms(new HashSet<>(filmRepository.findAllById(filmsIds)));
//        } else destination.setFilms(null);
//    }
//
//    @Override
//    public void mapSpecificFields(Creator source, CreatorDto destination) {
//        Set<Long> filmsIds = null;
//        Set<Film> films = source.getFilms();
//        if (films != null) {
//            filmsIds = films.stream()
//                    .map(GenericModel::getId)
//                    .collect(Collectors.toSet());
//        }
//        destination.setFilmsIds(filmsIds);
//    }
//
//
//    @Autowired
//    public void setFilmRepository(FilmRepository filmRepository) {
//        this.filmRepository = filmRepository;
//    }
}
