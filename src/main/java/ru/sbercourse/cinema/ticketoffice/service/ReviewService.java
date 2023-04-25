package ru.sbercourse.cinema.ticketoffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.sbercourse.cinema.ticketoffice.dto.ReviewDTO;
import ru.sbercourse.cinema.ticketoffice.mapper.ReviewMapper;
import ru.sbercourse.cinema.ticketoffice.model.Review;
import ru.sbercourse.cinema.ticketoffice.model.User;
import ru.sbercourse.cinema.ticketoffice.repository.ReviewRepository;
import ru.sbercourse.cinema.ticketoffice.repository.UserRepository;
import ru.sbercourse.cinema.ticketoffice.service.userdetails.CustomUserDetails;

import java.time.LocalDateTime;

@Service
public class ReviewService extends GenericService<Review, ReviewDTO> {

    private UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        repository = reviewRepository;
        mapper = reviewMapper;
    }


    @Override
    public ReviewDTO create(ReviewDTO reviewDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.valueOf(((CustomUserDetails) authentication.getPrincipal()).getUserId());
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Review review = mapper.toEntity(reviewDTO);
            review.setUser(user);
            review.setCreatedBy(authentication.getName());
            review.setCreatedWhen(LocalDateTime.now());
            return mapper.toDTO(repository.save(review));
        }
        return null;
    }



    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
