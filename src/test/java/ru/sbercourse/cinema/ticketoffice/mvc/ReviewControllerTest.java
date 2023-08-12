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

    private final Long EXISTING_REVIEW_ID = 1L;
    private final String EXISTING_LOGIN_WITH_USER_ROLE = "u";
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;



    @Test
    @WithUserDetails(EXISTING_LOGIN_WITH_USER_ROLE)
    void create() throws Exception {
        Long existingFilmId = 1L;
        mvc.perform(get("/reviews/add/" + existingFilmId))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("reviews/addReview"))
                .andExpect(model().attributeExists("film"));

        UserDTO existingUserDTO = userService.getByLogin(EXISTING_LOGIN_WITH_USER_ROLE);
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
        reviewService.restore(EXISTING_REVIEW_ID);
        ReviewDTO reviewForSoftDelete = reviewService.getById(EXISTING_REVIEW_ID);
        assertFalse(reviewForSoftDelete.isDeleted());

        mvc.perform(get("/reviews/delete/" + EXISTING_REVIEW_ID))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:" + null))
                .andExpect(redirectedUrl("null"));

        reviewForSoftDelete = reviewService.getById(EXISTING_REVIEW_ID);
        assertTrue(reviewForSoftDelete.isDeleted());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void restore() throws Exception {
        reviewService.softDelete(EXISTING_REVIEW_ID);
        ReviewDTO reviewForRestore = reviewService.getById(EXISTING_REVIEW_ID);
        assertTrue(reviewForRestore.isDeleted());

        mvc.perform(get("/reviews/restore/" + EXISTING_REVIEW_ID))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:" + null))
                .andExpect(redirectedUrl("null"));

        reviewForRestore = reviewService.getById(EXISTING_REVIEW_ID);
        assertFalse(reviewForRestore.isDeleted());
    }
}
