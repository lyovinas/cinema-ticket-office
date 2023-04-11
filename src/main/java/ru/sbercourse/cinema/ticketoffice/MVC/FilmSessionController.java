package ru.sbercourse.cinema.ticketoffice.MVC;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.sbercourse.cinema.ticketoffice.dto.FilmSessionDTO;
import ru.sbercourse.cinema.ticketoffice.service.FilmService;
import ru.sbercourse.cinema.ticketoffice.service.FilmSessionService;

@Controller
@RequestMapping("/filmSessions")
public class FilmSessionController {

    private FilmSessionService filmSessionService;
    private FilmService filmService;



    @PostMapping("/add")
    public String create(@ModelAttribute("filmSessionForm") FilmSessionDTO filmSessionDTO) {
        filmSessionService.create(filmSessionDTO);
        return "redirect:/films/get/" + filmSessionDTO.getFilmId();
    }

    @GetMapping("/delete/{id}")
    public String softDelete(@PathVariable Long id, HttpServletRequest request) {
        filmSessionService.softDelete(id);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable Long id, HttpServletRequest request) {
        filmSessionService.restore(id);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id) {
        model.addAttribute("filmSession", filmSessionService.getById(id));
        model.addAttribute("filmTitle", filmService.getById(id).getTitle());
        return "filmSessions/updateFilmSession";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("filmSessionForm") FilmSessionDTO filmSessionDTO) {
        filmSessionService.update(filmSessionDTO);
        return "redirect:/films/get/" + filmSessionDTO.getFilmId();
    }

//    @PostMapping("/search")
//    public String search(
//            @RequestParam(value = "page", defaultValue = "1") int page,
//            @RequestParam(value = "size", defaultValue = "5") int pageSize,
//            @ModelAttribute("filmCreatorSearchForm") FilmSessionDTO filmCreatorDTO,
//            Model model
//    ) {
//        if (StringUtils.hasText(filmCreatorDTO.getFullName())) {
//            PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "fullName"));
//            Page<FilmSessionDTO> filmCreatorDTOPage = filmSessionService.searchFilmCreators(filmCreatorDTO.getFullName().trim(), pageRequest);
//            model.addAttribute("filmCreators", filmCreatorDTOPage);
//            return "filmCreators/viewAllFilmCreators";
//        } else {
//            return "redirect:/filmCreators";
//        }
//    }



    @Autowired
    public void setFilmSessionService(FilmSessionService filmSessionService) {
        this.filmSessionService = filmSessionService;
    }

    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }
}
