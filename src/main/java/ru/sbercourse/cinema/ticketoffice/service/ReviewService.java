package ru.sbercourse.cinema.ticketoffice.service;

import org.springframework.stereotype.Service;
import ru.sbercourse.cinema.ticketoffice.dto.ReviewDTO;
import ru.sbercourse.cinema.ticketoffice.mapper.ReviewMapper;
import ru.sbercourse.cinema.ticketoffice.model.Review;
import ru.sbercourse.cinema.ticketoffice.repository.ReviewRepository;

@Service
public class ReviewService extends GenericService<Review, ReviewDTO> {

    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        repository = reviewRepository;
        mapper = reviewMapper;
    }
}
