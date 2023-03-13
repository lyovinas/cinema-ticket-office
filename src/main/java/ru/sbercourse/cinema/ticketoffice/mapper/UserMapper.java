package ru.sbercourse.cinema.ticketoffice.mapper;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbercourse.cinema.ticketoffice.dto.UserDto;
import ru.sbercourse.cinema.ticketoffice.model.GenericModel;
import ru.sbercourse.cinema.ticketoffice.model.Order;
import ru.sbercourse.cinema.ticketoffice.model.User;
import ru.sbercourse.cinema.ticketoffice.repository.OrderRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper extends GenericMapper<User, UserDto>
        implements ConverterForSpecificFields<User, UserDto>{

    private OrderRepository orderRepository;


    public UserMapper() {
        super(User.class, UserDto.class);
    }


    @PostConstruct
    @Override
    public void setupMapper() {
        modelMapper.createTypeMap(User.class, UserDto.class)
                .addMappings(m -> m.skip(UserDto::setOrdersIds))
                .setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(UserDto.class, User.class)
                .addMappings(m -> m.skip(User::setOrders))
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(UserDto source, User destination) {
        Set<Long> ordersIds = source.getOrdersIds();
        if (ordersIds != null) {
            destination.setOrders(new HashSet<>(orderRepository.findAllById(ordersIds)));
        } else destination.setOrders(null);
    }

    @Override
    public void mapSpecificFields(User source, UserDto destination) {
        //noinspection DuplicatedCode
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
