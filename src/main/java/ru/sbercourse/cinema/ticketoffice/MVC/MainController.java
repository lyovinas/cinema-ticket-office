package ru.sbercourse.cinema.ticketoffice.MVC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sbercourse.cinema.ticketoffice.service.FilmService;

import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class MainController {

  FilmService filmService;



  @GetMapping
  public String index(Model model) {
    model.addAttribute("films", filmService.getAllAvailable());
    model.addAttribute("today", LocalDate.now());
    model.addAttribute("tomorrow", LocalDate.now().plusDays(1));
    return "/index";
  }

  @PostMapping
  public String index(@ModelAttribute("startDate") LocalDate filmSessionStartDate, Model model) {
    model.addAttribute("films", filmService.getAllByDate(filmSessionStartDate));
    model.addAttribute("today", LocalDate.now());
    model.addAttribute("tomorrow", LocalDate.now().plusDays(1));
    return "/index";
  }



  @Autowired
  public void setFilmService(FilmService filmService) {
    this.filmService = filmService;
  }
}
