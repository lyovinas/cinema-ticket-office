package ru.sbercourse.cinema.ticketoffice.MVC;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sbercourse.cinema.ticketoffice.dto.ReviewDTO;
import ru.sbercourse.cinema.ticketoffice.service.FilmService;
import ru.sbercourse.cinema.ticketoffice.service.ReviewService;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private FilmService filmService;
    private ReviewService reviewService;



    @GetMapping("/add/{filmId}")
    public String create(@PathVariable Long filmId, Model model) {
        model.addAttribute("film", filmService.getById(filmId));
        return "/reviews/addReview";
    }

    @PostMapping("/add")
    public String create(ReviewDTO reviewDTO) {
        reviewService.create(reviewDTO);
        return "redirect:/films/get/" + reviewDTO.getFilmId();
    }

    @GetMapping("/delete/{id}")
    public String softDelete(@PathVariable Long id, HttpServletRequest request) {
        reviewService.softDelete(id);
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable Long id, HttpServletRequest request) {
        reviewService.restore(id);
        return "redirect:" + request.getHeader("Referer");
    }



    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }

    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
}
