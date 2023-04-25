package ru.sbercourse.cinema.ticketoffice.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.transaction.annotation.Transactional;
import ru.sbercourse.cinema.ticketoffice.dto.ReviewDTO;
import ru.sbercourse.cinema.ticketoffice.dto.UserDTO;
import ru.sbercourse.cinema.ticketoffice.service.ReviewService;
import ru.sbercourse.cinema.ticketoffice.service.UserService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
class ReviewControllerTest extends CommonTest {

    private final Long existingReviewId = 4L;
    private final String existingLoginWithUserRole = "u";
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;



    @Test
    @WithUserDetails(existingLoginWithUserRole)
    void create() throws Exception {
        Long existingFilmId = 1L;
        mvc.perform(get("/reviews/add/" + existingFilmId))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("reviews/addReview"))
                .andExpect(model().attributeExists("film"));

        UserDTO existingUserDTO = userService.getByLogin(existingLoginWithUserRole);
        ReviewDTO newReviewDTO = new ReviewDTO(existingFilmId, existingUserDTO, "MVC_Test");

        mvc.perform(post("/reviews/add")
                        .flashAttr("reviewForm", newReviewDTO)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/films/get/" + newReviewDTO.getFilmId()))
                .andExpect(redirectedUrl("/films/get/" + newReviewDTO.getFilmId()));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void softDelete() throws Exception {
        reviewService.restore(existingReviewId);
        ReviewDTO reviewForSoftDelete = reviewService.getById(existingReviewId);
        assertFalse(reviewForSoftDelete.isDeleted());

        mvc.perform(get("/reviews/delete/" + existingReviewId))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:" + null))
                .andExpect(redirectedUrl("null"));

        reviewForSoftDelete = reviewService.getById(existingReviewId);
        assertTrue(reviewForSoftDelete.isDeleted());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void restore() throws Exception {
        reviewService.softDelete(existingReviewId);
        ReviewDTO reviewForRestore = reviewService.getById(existingReviewId);
        assertTrue(reviewForRestore.isDeleted());

        mvc.perform(get("/reviews/restore/" + existingReviewId))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:" + null))
                .andExpect(redirectedUrl("null"));

        reviewForRestore = reviewService.getById(existingReviewId);
        assertFalse(reviewForRestore.isDeleted());
    }
}
