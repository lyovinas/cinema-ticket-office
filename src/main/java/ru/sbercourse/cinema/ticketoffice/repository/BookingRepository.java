package ru.sbercourse.cinema.ticketoffice.repository;

import org.springframework.stereotype.Repository;
import ru.sbercourse.cinema.ticketoffice.model.Booking;

@Repository
public interface BookingRepository extends GenericRepository<Booking> {
}
