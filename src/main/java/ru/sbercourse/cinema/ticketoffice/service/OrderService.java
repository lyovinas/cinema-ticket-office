package ru.sbercourse.cinema.ticketoffice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.sbercourse.cinema.ticketoffice.dto.DatePeriodDTO;
import ru.sbercourse.cinema.ticketoffice.dto.OrderDTO;
import ru.sbercourse.cinema.ticketoffice.dto.OrderInfoDTO;
import ru.sbercourse.cinema.ticketoffice.mapper.OrderMapper;
import ru.sbercourse.cinema.ticketoffice.model.Order;
import ru.sbercourse.cinema.ticketoffice.repository.OrderRepository;

import java.time.LocalDateTime;

@Service
public class OrderService extends GenericService<Order, OrderDTO> {

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        repository = orderRepository;
        mapper = orderMapper;
    }



    public Page<OrderInfoDTO> getAllInfoByUserId(Long userId, PageRequest pageRequest) {
        return  ((OrderRepository) repository).getAllInfoByUserId(userId, pageRequest);
    }

    public Long getTotalCost(DatePeriodDTO datePeriodDTO) {
        return  ((OrderRepository) repository).getTotalCost(datePeriodDTO.getStartDate(), datePeriodDTO.getEndDate().plusDays(1));
    }

    public Long getTotalTickets(DatePeriodDTO datePeriodDTO) {
        return  ((OrderRepository) repository).getTotalTickets(datePeriodDTO.getStartDate(), datePeriodDTO.getEndDate().plusDays(1));
    }

    public OrderDTO purchase(OrderDTO orderDTO) {
        OrderDTO existingOrderDTO = getById(orderDTO.getId());
        if (existingOrderDTO != null) {
            existingOrderDTO.setPurchase(true);
            existingOrderDTO.setCreatedWhen(LocalDateTime.now());
            update(existingOrderDTO);
        }
        return existingOrderDTO;
    }
}
