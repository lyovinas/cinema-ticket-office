package ru.sbercourse.cinema.ticketoffice.mapper;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbercourse.cinema.ticketoffice.dto.UserDTO;
import ru.sbercourse.cinema.ticketoffice.model.GenericModel;
import ru.sbercourse.cinema.ticketoffice.model.Order;
import ru.sbercourse.cinema.ticketoffice.model.User;
import ru.sbercourse.cinema.ticketoffice.repository.OrderRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper extends GenericMapper<User, UserDTO>
        implements ConverterForSpecificFields<User, UserDTO>{

    private OrderRepository orderRepository;



    public UserMapper() {
        super(User.class, UserDTO.class);
    }



    @PostConstruct
    @Override
    public void setupMapper() {
        modelMapper.createTypeMap(User.class, UserDTO.class)
                .addMappings(m -> m.skip(UserDTO::setOrdersIds))
                .setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(UserDTO.class, User.class)
                .addMappings(m -> m.skip(User::setOrders))
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(UserDTO source, User destination) {
        Set<Long> ordersIds = source.getOrdersIds();
        if (ordersIds != null) {
            destination.setOrders(new HashSet<>(orderRepository.findAllById(ordersIds)));
        } else destination.setOrders(null);
    }

    @Override
    public void mapSpecificFields(User source, UserDTO destination) {
        Set<Long> ordersIds = null;
        Set<Order> orders = source.getOrders();
        if (orders != null) {
            ordersIds = orders.stream()
                    .map(GenericModel::getId)
                    .collect(Collectors.toSet());
        }
        destination.setOrdersIds(ordersIds);
    }



    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
