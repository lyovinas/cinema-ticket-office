package ru.sbercourse.cinema.ticketoffice.MVC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sbercourse.cinema.ticketoffice.dto.AddFilmCreatorDTO;
import ru.sbercourse.cinema.ticketoffice.dto.FilmDTO;
import ru.sbercourse.cinema.ticketoffice.dto.FilmSearchDTO;
import ru.sbercourse.cinema.ticketoffice.service.FilmCreatorService;
import ru.sbercourse.cinema.ticketoffice.service.FilmService;

@Controller
@RequestMapping("/films")
public class FilmController {

    private FilmService filmService;
    private FilmCreatorService filmCreatorService;



    @GetMapping("")
    public String getAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "4") int pageSize,
            Model model
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "createdWhen"));
        model.addAttribute("films", filmService.getAll(pageRequest));
        return "films/viewAllFilms";
    }

    @GetMapping("/{id}")
    public String viewFilm(@PathVariable Long id, Model model) {
        model.addAttribute("film", filmService.getWithSessionsById(id));
        return "/films/viewFilm";
    }

    @GetMapping("/add")
    public String create() {
        return "films/addFilm";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("filmForm") FilmDTO filmDTO) {
        filmService.create(filmDTO);
        return "redirect:/films";
    }

    @GetMapping("/delete/{id}")
    public String softDelete(@PathVariable Long id) {
        filmService.softDelete(id);
        return "redirect:/films";
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable Long id) {
        filmService.restore(id);
        return "redirect:/films";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id) {
        model.addAttribute("film", filmService.getById(id));
//        model.addAttribute("filmCreators", filmCreatorService.getAll());
        return "films/updateFilm";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("filmForm") FilmDTO filmDTO) {
        filmService.update(filmDTO.getId(), filmDTO);
        return "redirect:/films/" + filmDTO.getId();
    }

    @GetMapping("/{filmId}/addFilmCreator")
    public String addFilmCreator(@PathVariable Long filmId, Model model) {
        model.addAttribute("film", filmService.getById(filmId));
        model.addAttribute("filmCreators", filmCreatorService.getAll());
        return "/films/addFilmCreator";
    }

    @PostMapping("/addFilmCreator")
    public String addFilmCreator(@ModelAttribute("filmCreatorAddForm") AddFilmCreatorDTO addFilmCreatorDTO) {
        filmService.addFilmCreator(addFilmCreatorDTO.getFilmId(), addFilmCreatorDTO.getFilmCreatorId());
        return "redirect:/films/update/" + addFilmCreatorDTO.getFilmId();
    }

    @GetMapping("/{filmId}/deleteFilmCreator/{filmCreatorId}")
    public String deleteFilmCreator(@PathVariable Long filmId, @PathVariable Long filmCreatorId) {
        filmService.deleteFilmCreator(filmId, filmCreatorId);
        return "redirect:/films/update/" + filmId;
    }

    @PostMapping("/search")
    public String search(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "4") int pageSize,
            @ModelAttribute("filmSearchForm") FilmSearchDTO filmSearchDTO,
            Model model
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "title"));
        model.addAttribute("films", filmService.findFilms(filmSearchDTO, pageRequest));
        return "films/viewAllFilms";
    }



    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }

    @Autowired
    public void setFilmCreatorService(FilmCreatorService filmCreatorService) {
        this.filmCreatorService = filmCreatorService;
    }
}
