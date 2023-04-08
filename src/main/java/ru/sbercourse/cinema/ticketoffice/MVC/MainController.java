package ru.sbercourse.cinema.ticketoffice.MVC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sbercourse.cinema.ticketoffice.service.FilmService;

@Controller
public class MainController {

  FilmService filmService;



  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("films", filmService.getAllWithSessions());
    return "index";
  }



  @Autowired
  public void setFilmService(FilmService filmService) {
    this.filmService = filmService;
  }
}
