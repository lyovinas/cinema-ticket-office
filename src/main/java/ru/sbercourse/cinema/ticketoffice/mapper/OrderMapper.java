package ru.sbercourse.cinema.ticketoffice.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbercourse.cinema.ticketoffice.dto.OrderDto;
import ru.sbercourse.cinema.ticketoffice.model.FilmSession;
import ru.sbercourse.cinema.ticketoffice.model.Order;
import ru.sbercourse.cinema.ticketoffice.model.Placement;
import ru.sbercourse.cinema.ticketoffice.model.User;
import ru.sbercourse.cinema.ticketoffice.repository.FilmSessionRepository;
import ru.sbercourse.cinema.ticketoffice.repository.PlacementRepository;
import ru.sbercourse.cinema.ticketoffice.repository.UserRepository;

@Component
public class OrderMapper extends GenericMapper<Order, OrderDto> implements ConverterForSpecificFields<Order, OrderDto>{

    private UserRepository userRepository;
    private FilmSessionRepository filmSessionRepository;
    private PlacementRepository placementRepository;



    public OrderMapper() {
        super(Order.class, OrderDto.class);
    }



    @Override
    public void setupMapper() {
        modelMapper.createTypeMap(Order.class, OrderDto.class)
                .addMappings(m -> {
                    m.skip(OrderDto::setUserId);
                    m.skip(OrderDto::setFilmSessionId);
                    m.skip(OrderDto::setPlacementId);
                })
                .setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(OrderDto.class, Order.class)
                .addMappings(m -> {
                    m.skip(Order::setUser);
                    m.skip(Order::setFilmSession);
                    m.skip(Order::setPlacement);
                })
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(OrderDto source, Order destination) {
        Long userId = source.getUserId();
        if (userId != null) {
            destination.setUser(userRepository.findById(userId).orElse(null));
        } else destination.setUser(null);

        Long filmSessionId = source.getFilmSessionId();
        if (filmSessionId != null) {
            destination.setFilmSession(filmSessionRepository.findById(filmSessionId).orElse(null));
        } else destination.setFilmSession(null);

        Long placementId = source.getPlacementId();
        if (placementId != null) {
            destination.setPlacement(placementRepository.findById(placementId).orElse(null));
        } else destination.setPlacement(null);
    }

    @Override
    public void mapSpecificFields(Order source, OrderDto destination) {
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

        Long placementId = null;
        Placement placement = source.getPlacement();
        if (placement != null) {
            placementId = placement.getId();
        }
        destination.setPlacementId(placementId);
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
    public void setPlacementRepository(PlacementRepository placementRepository) {
        this.placementRepository = placementRepository;
    }
}
