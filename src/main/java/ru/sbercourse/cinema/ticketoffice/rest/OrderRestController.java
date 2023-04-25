package ru.sbercourse.cinema.ticketoffice.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercourse.cinema.ticketoffice.dto.OrderDTO;
import ru.sbercourse.cinema.ticketoffice.mapper.OrderMapper;
import ru.sbercourse.cinema.ticketoffice.model.Order;
import ru.sbercourse.cinema.ticketoffice.service.OrderService;
import ru.sbercourse.cinema.ticketoffice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Заказы", description = "Контроллер для работы с заказами посетителей кинотеатра")
public class OrderRestController extends GenericRestController<Order, OrderDTO> {

    private UserService userService;
    private OrderMapper orderMapper;


    public OrderRestController(OrderService orderService) {
        service = orderService;
    }



    @Operation(summary = "Получить заказы по ID пользователя",
            description = "Позволяет получить список заказов по заданному ID пользователя")
    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDTO>> getByUserId(
            @PathVariable("userId") @Parameter(description = "Идентификатор пользователя") Long userId) {
        List<Order> orders = userService.getOrders(userId);
        return orders != null
                ? ResponseEntity.ok(orderMapper.toDTOs(orders))
                : ResponseEntity.notFound().build();
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }
}
