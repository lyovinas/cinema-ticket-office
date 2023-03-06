package ru.sbercourse.cinema.ticketoffice.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercourse.cinema.ticketoffice.model.Order;
import ru.sbercourse.cinema.ticketoffice.repository.OrderRepository;

@RestController
@RequestMapping("/orders")
@Tag(name = "Заказы", description = "Контроллер для работы с заказами посетителей кинотеатра")
public class OrderController extends GenericController<Order> {

    public OrderController(OrderRepository orderRepository) {
        setRepository(orderRepository);
    }
}
