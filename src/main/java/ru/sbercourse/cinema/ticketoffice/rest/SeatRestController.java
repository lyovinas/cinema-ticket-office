package ru.sbercourse.cinema.ticketoffice.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercourse.cinema.ticketoffice.dto.SeatDTO;
import ru.sbercourse.cinema.ticketoffice.model.Seat;
import ru.sbercourse.cinema.ticketoffice.service.SeatService;

@RestController
@RequestMapping("/seats")
@Tag(name = "Места", description = "Контроллер для работы с местами в кинозале")
public class SeatRestController extends GenericRestController<Seat, SeatDTO> {

    public SeatRestController(SeatService seatService) {
        service = seatService;
    }
}
