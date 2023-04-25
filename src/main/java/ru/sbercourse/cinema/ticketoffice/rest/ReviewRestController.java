package ru.sbercourse.cinema.ticketoffice.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercourse.cinema.ticketoffice.dto.ReviewDTO;
import ru.sbercourse.cinema.ticketoffice.model.Review;
import ru.sbercourse.cinema.ticketoffice.service.ReviewService;

@RestController
@RequestMapping("/reviews")
@Tag(name = "Отзывы", description = "Контроллер для работы с отзывами пользователей о фильмах")
public class ReviewRestController extends GenericRestController<Review, ReviewDTO> {

    public ReviewRestController(ReviewService reviewService) {
        service = reviewService;
    }
}
