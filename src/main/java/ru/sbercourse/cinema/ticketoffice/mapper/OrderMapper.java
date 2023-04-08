package ru.sbercourse.cinema.ticketoffice.mapper;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbercourse.cinema.ticketoffice.dto.OrderDTO;
import ru.sbercourse.cinema.ticketoffice.model.FilmSession;
import ru.sbercourse.cinema.ticketoffice.model.Order;
import ru.sbercourse.cinema.ticketoffice.model.Seat;
import ru.sbercourse.cinema.ticketoffice.model.User;
import ru.sbercourse.cinema.ticketoffice.repository.FilmSessionRepository;
import ru.sbercourse.cinema.ticketoffice.repository.SeatRepository;
import ru.sbercourse.cinema.ticketoffice.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderMapper extends GenericMapper<Order, OrderDTO>
        implements ConverterForSpecificFields<Order, OrderDTO>{

    private UserRepository userRepository;
    private FilmSessionRepository filmSessionRepository;
    private SeatRepository seatRepository;



    public OrderMapper() {
        super(Order.class, OrderDTO.class);
    }



    @PostConstruct
    @Override
    public void setupMapper() {
        modelMapper.createTypeMap(Order.class, OrderDTO.class)
                .addMappings(m -> {
                    m.skip(OrderDTO::setUserId);
                    m.skip(OrderDTO::setFilmSessionId);
                    m.skip(OrderDTO::setSeatIds);
                })
                .setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(OrderDTO.class, Order.class)
                .addMappings(m -> {
                    m.skip(Order::setUser);
                    m.skip(Order::setFilmSession);
                    m.skip(Order::setSeats);
                })
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(OrderDTO source, Order destination) {
        Long userId = source.getUserId();
        if (userId != null) {
            destination.setUser(userRepository.findById(userId).orElse(null));
        } else destination.setUser(null);

        Long filmSessionId = source.getFilmSessionId();
        if (filmSessionId != null) {
            destination.setFilmSession(filmSessionRepository.findById(filmSessionId).orElse(null));
        } else destination.setFilmSession(null);

        Set<Long> seatIds = source.getSeatIds();
        if (seatIds != null) {
            destination.setSeats(new HashSet<>(seatRepository.findAllById(seatIds)));
        } else destination.setSeats(null);
    }

    @Override
    public void mapSpecificFields(Order source, OrderDTO destination) {
        Long userId = null;
        User user = source.getUser();
        if (user != null) {
            userId = user.getId();
        }
        destination.setUserId(userId);

        Long filmSessionId = null;
        FilmSession filmSession = source.getFilmSession();
        if (filmSession != null) {
            filmSessionId = filmSession.getId();
        }
        destination.setFilmSessionId(filmSessionId);

        Set<Long> seatIds = null;
        Set<Seat> seats = source.getSeats();
        if (seats != null) {
            seatIds = seats.stream().map(Seat::getId).collect(Collectors.toSet());
        }
        destination.setSeatIds(seatIds);
    }



    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setFilmSessionRepository(FilmSessionRepository filmSessionRepository) {
        this.filmSessionRepository = filmSessionRepository;
    }

    @Autowired
    public void setSeatRepository(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }
}
