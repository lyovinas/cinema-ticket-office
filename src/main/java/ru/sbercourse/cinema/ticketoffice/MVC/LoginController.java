package ru.sbercourse.cinema.ticketoffice.MVC;

//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

  @GetMapping("/login")
  public String login() {
//    if (
//        SecurityContextHolder.getContext().getAuthentication() != null &&
//        SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
//        !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)
//    ) {
//      return "redirect:/";
//    }
    return "users/login";
  }
}
