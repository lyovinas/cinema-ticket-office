package ru.sbercourse.cinema.ticketoffice.MVC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.sbercourse.cinema.ticketoffice.dto.FilmCreatorDTO;
import ru.sbercourse.cinema.ticketoffice.service.FilmCreatorService;

@Controller
@RequestMapping("/filmCreators")
public class FilmCreatorController {

    private FilmCreatorService filmCreatorService;



    @GetMapping("")
    public String getAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int pageSize,
            Model model
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "fullName", "position"));
        Page<FilmCreatorDTO> filmCreatorDTOPage = filmCreatorService.getAll(pageRequest);
        model.addAttribute("filmCreators", filmCreatorDTOPage);
        return "filmCreators/viewAllFilmCreators";
    }

//    @GetMapping("/{id}")
//    public String viewFilmCreator(@PathVariable Long id, Model model) {
//        model.addAttribute("filmCreator", filmCreatorService.getById(id));
//        return "/filmCreators/viewFilmCreator";
//    }

    @GetMapping("/add")
    public String create() {
        return "filmCreators/addFilmCreator";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("filmCreatorForm") FilmCreatorDTO filmCreatorDTO) {
        filmCreatorService.create(filmCreatorDTO);
        return "redirect:/filmCreators";
    }

    @GetMapping("/delete/{id}")
    public String softDelete(@PathVariable Long id) {
        filmCreatorService.softDelete(id);
        return "redirect:/filmCreators";
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable Long id) {
        filmCreatorService.restore(id);
        return "redirect:/filmCreators";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id) {
        model.addAttribute("filmCreator", filmCreatorService.getById(id));
        return "filmCreators/updateFilmCreator";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("filmCreatorForm") FilmCreatorDTO filmCreatorDTO) {
        filmCreatorService.update(filmCreatorDTO.getId(), filmCreatorDTO);
        return "redirect:/filmCreators";
    }

    @PostMapping("/search")
    public String search(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int pageSize,
            @ModelAttribute("filmCreatorSearchForm") FilmCreatorDTO filmCreatorDTO,
            Model model
    ) {
        if (StringUtils.hasText(filmCreatorDTO.getFullName())) {
            PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "fullName"));
            Page<FilmCreatorDTO> filmCreatorDTOPage = filmCreatorService.searchFilmCreators(filmCreatorDTO.getFullName().trim(), pageRequest);
            model.addAttribute("filmCreators", filmCreatorDTOPage);
            return "filmCreators/viewAllFilmCreators";
        } else {
            return "redirect:/filmCreators";
        }
    }



    @Autowired
    public void setFilmCreatorService(FilmCreatorService filmCreatorService) {
        this.filmCreatorService = filmCreatorService;
    }
}
