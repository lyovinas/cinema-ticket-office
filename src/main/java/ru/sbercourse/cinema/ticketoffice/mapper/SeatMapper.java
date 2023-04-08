package ru.sbercourse.cinema.ticketoffice.mapper;

import org.springframework.stereotype.Component;
import ru.sbercourse.cinema.ticketoffice.dto.SeatDTO;
import ru.sbercourse.cinema.ticketoffice.model.Seat;

@Component
public class SeatMapper extends GenericMapper<Seat, SeatDTO> {

    public SeatMapper() {
        super(Seat.class, SeatDTO.class);
    }

}
