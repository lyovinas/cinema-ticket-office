package ru.sbercourse.cinema.ticketoffice.garbage;

import org.springframework.stereotype.Service;
import ru.sbercourse.cinema.ticketoffice.service.GenericService;

//@Service
 class BookingService extends GenericService<Booking, BookingDto> {

    public BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        setRepository(bookingRepository);
        setMapper(bookingMapper);
    }
}
