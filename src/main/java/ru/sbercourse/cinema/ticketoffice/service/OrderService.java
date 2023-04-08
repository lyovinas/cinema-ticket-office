package ru.sbercourse.cinema.ticketoffice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.sbercourse.cinema.ticketoffice.dto.DatePeriodDTO;
import ru.sbercourse.cinema.ticketoffice.dto.OrderDTO;
import ru.sbercourse.cinema.ticketoffice.dto.OrderInfoDTO;
import ru.sbercourse.cinema.ticketoffice.mapper.OrderMapper;
import ru.sbercourse.cinema.ticketoffice.model.Order;
import ru.sbercourse.cinema.ticketoffice.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService extends GenericService<Order, OrderDTO> {

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        repository = orderRepository;
        mapper = orderMapper;
    }



    public List<Order> getByFilmSessionId(Long filmSessionId) {
        return filmSessionId != null
                ? ((OrderRepository) repository).getByFilmSessionId(filmSessionId)
                : null;
    }

//    @Override
//    public OrderDTO create(OrderDTO DTO) {
//        if (DTO == null) return null;
//        Long filmSessionId = DTO.getFilmSessionId();
//        Long seatId = DTO.getSeatId();
//        if (filmSessionId == null || seatId == null) return null;
//        Order existingOrder = ((OrderRepository) repository)
//                .getByFilmSessionIdAndSeatId(filmSessionId, seatId);
//        if (existingOrder != null) return null;
//        return super.create(DTO);
//    }

    public Page<OrderDTO> getAllByUserId(Long userId, PageRequest pageRequest) {
        Page<Order> page = ((OrderRepository) repository).getAllByUserId(userId, pageRequest);
        List<OrderDTO> result = mapper.toDTOs(page.getContent());
        return new PageImpl<>(result, pageRequest, page.getTotalElements());
    }

    public Page<OrderInfoDTO> getAllInfo(PageRequest pageRequest) {
        return  ((OrderRepository) repository).getAllInfo(pageRequest);
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
}
