package ru.sbercourse.cinema.ticketoffice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sbercourse.cinema.ticketoffice.dto.OrderInfoDTO;
import ru.sbercourse.cinema.ticketoffice.model.Order;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends GenericRepository<Order>{

    //TODO проверить необходимость - проверка занятости места при попытке заказа
//    Order getByFilmSessionIdAndSeatId(Long filmSessionId, Long seatId);

    List<Order> getByFilmSessionId(Long filmSessionId);//TODO у киносеанса есть заказы - заменить?

    Page<Order> getAllByUserId(Long userId, PageRequest pageRequest);

    @Query("SELECT new ru.sbercourse.cinema.ticketoffice.dto.OrderInfoDTO(o.id, o.createdWhen, f.title, o.cost, o.purchase) "
            + "FROM Film f JOIN f.filmSessions fs JOIN fs.orders o")
    Page<OrderInfoDTO> getAllInfo(PageRequest pageRequest);

    @Query("SELECT new ru.sbercourse.cinema.ticketoffice.dto.OrderInfoDTO(o.id, o.createdWhen, f.title, o.cost, o.purchase) "
            + "FROM Film f JOIN f.filmSessions fs JOIN fs.orders o WHERE o.user.id = ?1 AND o.isDeleted = false ORDER BY o.createdWhen DESC")
    Page<OrderInfoDTO> getAllInfoByUserId(Long userId, PageRequest pageRequest);

    @Query(nativeQuery = true,
            value = "select sum(cost) from orders where created_when between :startDate and :endDate")
    Long getTotalCost(@Param(value = "startDate") LocalDate startDate, @Param(value = "endDate") LocalDate endDate);

    @Query(nativeQuery = true,
            value = "select count(*) from orders where created_when between :startDate and :endDate")
    Long getTotalTickets(@Param(value = "startDate") LocalDate startDate, @Param(value = "endDate") LocalDate endDate);

//    @Query(nativeQuery = true,
//            value = """
//                    select o.id as orderId, o.created_when as orderCreatedWhen, o.cost as cost,
//                           o.purchase as purchase , f.title as filmTitle
//                    from orders o join film_sessions fs on fs.id = o.film_session_id
//                            join films f on f.id = fs.film_id
//                    """)
//    Page<OrderInfoDTO> getAllInfo(PageRequest pageRequest);
}
