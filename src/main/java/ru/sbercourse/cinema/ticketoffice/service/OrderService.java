package ru.sbercourse.cinema.ticketoffice.service;

import org.springframework.stereotype.Service;
import ru.sbercourse.cinema.ticketoffice.dto.OrderDto;
import ru.sbercourse.cinema.ticketoffice.mapper.OrderMapper;
import ru.sbercourse.cinema.ticketoffice.model.Order;
import ru.sbercourse.cinema.ticketoffice.repository.OrderRepository;

@Service
public class OrderService extends GenericService<Order, OrderDto> {

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        setRepository(orderRepository);
        setMapper(orderMapper);
    }
}
