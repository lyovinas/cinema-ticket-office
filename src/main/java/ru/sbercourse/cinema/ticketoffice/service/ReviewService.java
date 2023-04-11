package ru.sbercourse.cinema.ticketoffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.sbercourse.cinema.ticketoffice.dto.ReviewDTO;
import ru.sbercourse.cinema.ticketoffice.mapper.ReviewMapper;
import ru.sbercourse.cinema.ticketoffice.mapper.UserMapper;
import ru.sbercourse.cinema.ticketoffice.model.Review;
import ru.sbercourse.cinema.ticketoffice.repository.ReviewRepository;
import ru.sbercourse.cinema.ticketoffice.service.userdetails.CustomUserDetails;

import java.time.LocalDateTime;

@Service
public class ReviewService extends GenericService<Review, ReviewDTO> {

    private UserService userService;
    private UserMapper userMapper;

    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        repository = reviewRepository;
        mapper = reviewMapper;
    }


    @Override
    public ReviewDTO create(ReviewDTO reviewDTO) {
        Review review = mapper.toEntity(reviewDTO);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.valueOf(((CustomUserDetails) authentication.getPrincipal()).getUserId());
        review.setUser(userMapper.toEntity(userService.getById(userId)));
        review.setCreatedBy(authentication.getName());
        review.setCreatedWhen(LocalDateTime.now());

        return mapper.toDTO(repository.save(review));
    }



    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
