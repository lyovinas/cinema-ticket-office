package ru.sbercourse.cinema.ticketoffice.mvc;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sbercourse.cinema.ticketoffice.dto.UserDTO;
import ru.sbercourse.cinema.ticketoffice.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

  @Value("${spring.security.user.name}")
  private String adminUserName;
  private UserService userService;



  @GetMapping("")
  public String getAll(
      @RequestParam(value = "page", defaultValue = "1") int page,
      @RequestParam(value = "size", defaultValue = "5") int pageSize,
      Model model
  ) {
    PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Direction.ASC, "login"));
    model.addAttribute("users", userService.getAll(pageRequest));
    return "users/viewAllUsers";
  }

  @GetMapping("/profile/{id}")
  public String viewProfile(@PathVariable Long id, Model model) {
    model.addAttribute("user", userService.getById(id));
    return "users/viewProfile";
  }

  @GetMapping("/profile/update/{id}")
  public String updateProfile(@PathVariable Long id, Model model) {
    model.addAttribute("user", userService.getById(id));
    return "users/updateProfile";
  }

  @PostMapping("/profile/update")
  public String updateProfile(@ModelAttribute("userForm") UserDTO userDTO) {
    userService.update(userDTO);
    return "redirect:/users/profile/" + userDTO.getId();
  }

  @GetMapping("/registration")
  public String registration(@ModelAttribute("userForm") UserDTO userDTO) {
    return "users/registration";
  }

  @PostMapping("/registration")
  public String registration(@ModelAttribute("userForm") UserDTO userDTO, BindingResult bindingResult) {
    if(adminUserName.equals(userDTO.getLogin()) || userService.getByLogin(userDTO.getLogin()) != null) {
      bindingResult.rejectValue("login", "error.login", "Такой логин уже существует");
      return "users/registration";
    }
    if(userService.getByEmail(userDTO.getEmail()) != null) {
      bindingResult.rejectValue("email", "error.email", "Такая почта уже существует");
      return "users/registration";
    }
    userService.create(userDTO);
    return "redirect:/login";
  }

  @GetMapping("/add-manager")
  public String addManager(@ModelAttribute("userForm") UserDTO userDTO) {
    return "users/addManager";
  }

  @PostMapping("/add-manager")
  public String addManager(@ModelAttribute("userForm") UserDTO userDTO, BindingResult bindingResult) {
    if(adminUserName.equals(userDTO.getLogin()) || userService.getByLogin(userDTO.getLogin()) != null) {
      bindingResult.rejectValue("login", "error.login", "Такой логин уже существует");
      return "users/addManager";
    }
    if(userService.getByEmail(userDTO.getEmail()) != null) {
      bindingResult.rejectValue("email", "error.email", "Такая почта уже существует");
      return "users/addManager";
    }
    userService.createManager(userDTO);
    return "redirect:/users";
  }

  @GetMapping("remember-password")
  public String rememberPassword() {
    return "users/rememberPassword";
  }

  @PostMapping("remember-password")
  public String rememberPassword(@ModelAttribute("changePasswordForm") UserDTO userDTO) {
    userService.sendChangePasswordEmail(userDTO.getEmail());
    return "redirect:/login";
  }

  @GetMapping("/change-password")
  public String changePassword(@PathParam(value = "uuid") String uuid, Model model) {
    model.addAttribute("uuid", uuid);
    return "users/changePassword";
  }

  @PostMapping("change-password")
  public String changePassword(
          @PathParam(value = "uuid") String uuid,
          @ModelAttribute("changePasswordForm") UserDTO userDTO
  ) {
    userService.changePassword(uuid, userDTO.getPassword());
    return "redirect:/login";
  }

  @GetMapping("/delete/{id}")
  public String softDelete(@PathVariable Long id) {
    userService.softDelete(id);
    return "redirect:/users";
  }

  @GetMapping("/restore/{id}")
  public String restore(@PathVariable Long id) {
    userService.restore(id);
    return "redirect:/users";
  }



  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }
}
