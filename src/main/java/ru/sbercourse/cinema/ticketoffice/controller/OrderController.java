package ru.sbercourse.cinema.ticketoffice.controller;

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
import ru.sbercourse.cinema.ticketoffice.dto.OrderDto;
import ru.sbercourse.cinema.ticketoffice.model.Order;
import ru.sbercourse.cinema.ticketoffice.model.User;
import ru.sbercourse.cinema.ticketoffice.repository.UserRepository;
import ru.sbercourse.cinema.ticketoffice.service.OrderService;

import java.util.Set;

@RestController
@RequestMapping("/orders")
@Tag(name = "Заказы", description = "Контроллер для работы с заказами посетителей кинотеатра")
public class OrderController extends GenericController<Order, OrderDto> {

    private UserRepository userRepository;


    public OrderController(OrderService orderService) {
        setService(orderService);
    }



    @Operation(summary = "Получить заказы по ID пользователя",
            description = "Позволяет получить список заказов по заданному ID пользователя")
    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<OrderDto>> getByUserId(
            @PathVariable("userId") @Parameter(description = "Идентификатор пользователя") Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Set<Order> orders = user.getOrders();
            if (orders != null) {
                return ResponseEntity.ok(service.getMapper().toDtos(orders));
            }
        }
        return ResponseEntity.notFound().build();
    }



    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
