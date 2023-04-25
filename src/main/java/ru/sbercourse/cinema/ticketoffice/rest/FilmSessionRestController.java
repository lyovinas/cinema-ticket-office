package ru.sbercourse.cinema.ticketoffice.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercourse.cinema.ticketoffice.dto.FilmSessionDTO;
import ru.sbercourse.cinema.ticketoffice.model.FilmSession;
import ru.sbercourse.cinema.ticketoffice.service.FilmSessionService;

@RestController
@RequestMapping("/filmSessions")
@Tag(name = "Киносеансы", description = "Контроллер для работы с сеансами фильмов")
public class FilmSessionRestController extends GenericRestController<FilmSession, FilmSessionDTO> {

    public FilmSessionRestController(FilmSessionService filmSessionService) {
        service = filmSessionService;
    }

}


//    private FilmRepository filmRepository;
//    private OrderService orderService;
//    private SeatMapper seatMapper;



//    @Operation(summary = "Получить занятые места по ID киносеанса",
//            description = "Позволяет получить список занятых мест для заданного киносеанса")
//    @GetMapping(value = "/getNotAvailableSeats/{filmSessionId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<SeatDTO>> getNotAvailableSeats(
//            @PathVariable("filmSessionId") @Parameter(description = "Идентификатор пользователя") Long filmSessionId) {
//        List<Order> orders = orderService.getByFilmSessionId(filmSessionId);
//        if (orders != null && !orders.isEmpty()) {
//            List<Seat> seats = orders.stream().map(Order::getSeat).toList();
//            if (!seats.isEmpty()) {
//                return ResponseEntity.ok(seatMapper.toDTOs(seats));
//            }
//        }
//        return ResponseEntity.notFound().build();
//    }

//    @Operation(summary = "Получить актуальные киносеансы",
//            description = "Позволяет получить список актуальных киносеансов")
//    @GetMapping(value = "/actual", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Set<FilmSessionDTO>> getActual() {
//        Set<FilmSession> filmSessions = ((FilmSessionRepository) service.getRepository()).getActual();
//        return filmSessions != null
//                ? ResponseEntity.ok(service.getMapper().toDTOs(filmSessions))
//                : ResponseEntity.notFound().build();
//    }

//    @Operation(summary = "Получить киносеансы по ID фильма",
//            description = "Позволяет получить список киносеансов по заданному ID фильма")
//    @GetMapping(value = "/film/{filmId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Set<FilmSessionDTO>> getByFilmId(
//            @PathVariable("filmId") @Parameter(description = "Идентификатор фильма") Long filmId) {
//        Film film = filmRepository.findById(filmId).orElse(null);
//        if (film != null) {
//            Set<FilmSession> filmSessions = film.getFilmSessions();
//            if (filmSessions != null) {
//                return ResponseEntity.ok(service.getMapper().toDTOs(filmSessions));
//            }
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//
//    @Autowired
//    public void setFilmRepository(FilmRepository filmRepository) {
//        this.filmRepository = filmRepository;
//    }

//    @Autowired
//    public void setOrderService(OrderService orderService) {
//        this.orderService = orderService;
//    }

//    @Autowired
//    public void setSeatMapper(SeatMapper seatMapper) {
//        this.seatMapper = seatMapper;
//    }
