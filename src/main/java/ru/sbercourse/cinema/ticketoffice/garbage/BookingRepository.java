package ru.sbercourse.cinema.ticketoffice.garbage;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import ru.sbercourse.cinema.ticketoffice.garbage.Booking;
import ru.sbercourse.cinema.ticketoffice.repository.GenericRepository;

//@Repository
@NoRepositoryBean
 interface BookingRepository extends GenericRepository<Booking> {
}
