package ru.sbercourse.cinema.ticketoffice.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercourse.cinema.ticketoffice.model.Booking;
import ru.sbercourse.cinema.ticketoffice.repository.BookingRepository;

@RestController
@RequestMapping("/bookings")
@Tag(name = "Бронирования", description = "Контроллер для работы с бронированием билетов в кинотеатр")
public class BookingController extends GenericController<Booking> {

    public BookingController(BookingRepository bookingRepository) {
        setRepository(bookingRepository);
    }
}
