package ru.sbercourse.cinema.ticketoffice.mapper;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbercourse.cinema.ticketoffice.dto.FilmSessionDto;
import ru.sbercourse.cinema.ticketoffice.model.Film;
import ru.sbercourse.cinema.ticketoffice.model.FilmSession;
import ru.sbercourse.cinema.ticketoffice.model.GenericModel;
import ru.sbercourse.cinema.ticketoffice.model.Order;
import ru.sbercourse.cinema.ticketoffice.repository.FilmRepository;
import ru.sbercourse.cinema.ticketoffice.repository.OrderRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmSessionMapper extends GenericMapper<FilmSession, FilmSessionDto>
        implements ConverterForSpecificFields<FilmSession, FilmSessionDto> {

    private FilmRepository filmRepository;
//    private OrderRepository orderRepository;


    public FilmSessionMapper() {
        super(FilmSession.class, FilmSessionDto.class);
    }


    @PostConstruct
    @Override
    public void setupMapper() {
        modelMapper.createTypeMap(FilmSession.class, FilmSessionDto.class)
                .addMappings(m -> {
                    m.skip(FilmSessionDto::setFilmId);
//                    m.skip(FilmSessionDto::setOrdersIds);
                })
                .setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(FilmSessionDto.class, FilmSession.class)
                .addMappings(m -> {
                    m.skip(FilmSession::setFilm);
//                    m.skip(FilmSession::setOrders);
                })
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(FilmSessionDto source, FilmSession destination) {
        //noinspection DuplicatedCode
        Long filmId = source.getFilmId();
        if (filmId != null) {
            destination.setFilm(filmRepository.findById(filmId).orElse(null));
        } else destination.setFilm(null);

//        Set<Long> ordersIds = source.getOrdersIds();
//        if (ordersIds != null) {
//            destination.setOrders(new HashSet<>(orderRepository.findAllById(ordersIds)));
//        } else destination.setOrders(null);
    }

    @Override
    public void mapSpecificFields(FilmSession source, FilmSessionDto destination) {
        Long filmId = null;
        Film film = source.getFilm();
        if (film != null) {
            filmId = film.getId();
        }
        destination.setFilmId(filmId);

        //noinspection DuplicatedCode
//        Set<Long> ordersIds = null;
//        Set<Order> orders = source.getOrders();
//        if (orders != null) {
//            ordersIds = orders.stream()
//                    .map(GenericModel::getId)
//                    .collect(Collectors.toSet());
//        }
//        destination.setOrdersIds(ordersIds);
    }


    @Autowired
    public void setFilmRepository(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

//    @Autowired
//    public void setOrderRepository(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }
}
