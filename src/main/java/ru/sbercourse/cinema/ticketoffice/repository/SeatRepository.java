package ru.sbercourse.cinema.ticketoffice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sbercourse.cinema.ticketoffice.dto.SeatsMapDTO;
import ru.sbercourse.cinema.ticketoffice.model.Seat;

import java.util.List;

@Repository
public interface SeatRepository extends GenericRepository<Seat> {

    @Query(nativeQuery = true,
        value = """
            select *
                from seats s
                where cast(s.row as varchar(2)) like coalesce(:row, '%')
                and cast(s.place as varchar(2)) like coalesce(:place,'%')
        """)
    Page<Seat> getAllByRowAndPlace(@Param(value = "row") String row, @Param(value = "place") String place, Pageable pageable);

    @Query(nativeQuery = true,
        value = """
            select s.id as seatId, s.row, s.place, o.id as orderId, s.is_deleted as deleted
                from orders o
                join orders_seats os on o.id = os.order_id and o.is_deleted = false and o.film_session_id = :filmSessionId
                right join seats s on s.id = os.seat_id
        """)
    List<SeatsMapDTO> getSeatsMap(@Param("filmSessionId") Long filmSessionId);
}

//    @Query("SELECT new ru.sbercourse.cinema.ticketoffice.dao.SeatsMapDAO(s.id, s.row, s.place, o.id, s.isDeleted) "
//            + "FROM Order o RIGHT JOIN o.seats s WHERE o.isDeleted = false")
//    @Query("SELECT new ru.sbercourse.cinema.ticketoffice.dao.SeatsMapDAO(s.id, s.row, s.place, o.id, s.isDeleted) "
//            + "FROM Seat s LEFT JOIN s.orders o")
