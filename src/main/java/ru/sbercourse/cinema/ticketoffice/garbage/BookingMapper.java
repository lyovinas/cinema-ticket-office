package ru.sbercourse.cinema.ticketoffice.garbage;

import org.springframework.stereotype.Component;
import ru.sbercourse.cinema.ticketoffice.garbage.BookingDto;
import ru.sbercourse.cinema.ticketoffice.garbage.Booking;
import ru.sbercourse.cinema.ticketoffice.mapper.GenericMapper;

//@Component
 class BookingMapper extends GenericMapper<Booking, BookingDto> {

    public BookingMapper() {
        super(Booking.class, BookingDto.class);
    }
}
