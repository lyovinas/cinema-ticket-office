package ru.sbercourse.cinema.ticketoffice.mapper;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbercourse.cinema.ticketoffice.dto.FilmSessionDTO;
import ru.sbercourse.cinema.ticketoffice.model.Film;
import ru.sbercourse.cinema.ticketoffice.model.FilmSession;
import ru.sbercourse.cinema.ticketoffice.repository.FilmRepository;

@Component
public class FilmSessionMapper extends GenericMapper<FilmSession, FilmSessionDTO>
        implements ConverterForSpecificFields<FilmSession, FilmSessionDTO> {

    private FilmRepository filmRepository;
//    private OrderRepository orderRepository;


    public FilmSessionMapper() {
        super(FilmSession.class, FilmSessionDTO.class);
    }


    @PostConstruct
    @Override
    public void setupMapper() {
        modelMapper.createTypeMap(FilmSession.class, FilmSessionDTO.class)
                .addMappings(m -> {
                    m.skip(FilmSessionDTO::setFilmId);
//                    m.skip(FilmSessionDto::setOrdersIds);
                })
                .setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(FilmSessionDTO.class, FilmSession.class)
                .addMappings(m -> {
                    m.skip(FilmSession::setFilm);
//                    m.skip(FilmSession::setOrders);
                })
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(FilmSessionDTO source, FilmSession destination) {
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
    public void mapSpecificFields(FilmSession source, FilmSessionDTO destination) {
        Long filmId = null;
        Film film = source.getFilm();
        if (film != null) {
            filmId = film.getId();
        }
        destination.setFilmId(filmId);

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
