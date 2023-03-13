package ru.sbercourse.cinema.ticketoffice.garbage;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercourse.cinema.ticketoffice.controller.GenericController;
import ru.sbercourse.cinema.ticketoffice.garbage.BookingDto;
import ru.sbercourse.cinema.ticketoffice.garbage.Booking;
import ru.sbercourse.cinema.ticketoffice.garbage.BookingService;

//@RestController
@RequestMapping("/bookings")
@Tag(name = "Бронирования", description = "Контроллер для работы с бронированием билетов в кинотеатр")
 class BookingController extends GenericController<Booking, BookingDto> {

    public BookingController(BookingService bookingService) {
        setService(bookingService);
    }
}
