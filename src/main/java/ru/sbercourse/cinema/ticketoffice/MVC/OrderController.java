package ru.sbercourse.cinema.ticketoffice.MVC;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sbercourse.cinema.ticketoffice.dto.DatePeriodDTO;
import ru.sbercourse.cinema.ticketoffice.dto.FilmSessionDTO;
import ru.sbercourse.cinema.ticketoffice.dto.OrderDTO;
import ru.sbercourse.cinema.ticketoffice.service.FilmService;
import ru.sbercourse.cinema.ticketoffice.service.FilmSessionService;
import ru.sbercourse.cinema.ticketoffice.service.OrderService;
import ru.sbercourse.cinema.ticketoffice.service.SeatService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/orders")
public class OrderController {

    OrderService orderService;
    SeatService seatService;
    FilmSessionService filmSessionService;
    FilmService filmService;



    @GetMapping("")
    public String getAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int pageSize,
            Model model
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "createdWhen"));
        model.addAttribute("orders", orderService.getAll(pageRequest));
        return "orders/viewAllOrders";
    }

    @GetMapping("/get/{id}")
    public String getAll(@PathVariable("id") Long id, Model model) {
        OrderDTO orderDTO = orderService.getById(id);
        FilmSessionDTO filmSessionDTO = filmSessionService.getById(orderDTO.getFilmSessionId());
        model.addAttribute("order", orderDTO);
        model.addAttribute("orderForm", new OrderDTO());
        model.addAttribute("filmTitle", filmService.getById(filmSessionDTO.getFilmId()).getTitle());
        model.addAttribute("filmSession", filmSessionDTO);
        model.addAttribute("seats", seatService.getAllByIds(orderDTO.getSeatIds()));
        model.addAttribute("canPurchased",
                LocalDateTime.of(filmSessionDTO.getStartDate(), filmSessionDTO.getStartTime())
                        .isAfter(LocalDateTime.now()));
        return "orders/viewOrder";
    }

    @GetMapping("/user/{userId}")
    public String getAllInfoByUserId(
            @PathVariable Long userId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int pageSize,
            Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        model.addAttribute("orders", orderService.getAllInfoByUserId(userId, pageRequest));
        model.addAttribute("userId", userId);
        return "/orders/viewAllUserOrders";
    }

    @PostMapping("/preview")
    public String previewOrder(@ModelAttribute("orderForm") OrderDTO orderDTO, Model model, HttpServletRequest request) {
        if (orderDTO.getSeatIds() == null) return "redirect:" + request.getHeader("Referer");
        FilmSessionDTO filmSessionDTO = filmSessionService.getById(orderDTO.getFilmSessionId());
        model.addAttribute("filmTitle", filmService.getById(filmSessionDTO.getFilmId()).getTitle());
        model.addAttribute("filmSession", filmSessionDTO);
        model.addAttribute("seats", seatService.getAllByIds(orderDTO.getSeatIds()));
        return "/orders/preview";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute("orderForm") OrderDTO orderDTO) {
        orderService.create(orderDTO);
        return "redirect:/orders/user/" + orderDTO.getUserId();
    }

    @PostMapping("/purchase")
    public String purchase(@ModelAttribute("orderForm") OrderDTO orderDTO) {
        OrderDTO existingOrderDTO = orderService.getById(orderDTO.getId());
        existingOrderDTO.setPurchase(true);
        existingOrderDTO.setCreatedWhen(LocalDateTime.now());
        orderService.update(existingOrderDTO);
        return "redirect:/orders/user/" + existingOrderDTO.getUserId();
    }

    @GetMapping("/delete/{id}")
    public String softDelete(@PathVariable Long id, HttpServletRequest request) {
        orderService.softDelete(id);
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable Long id) {
        orderService.restore(id);
        return "redirect:/orders";
    }

    @GetMapping("/results")
    public String getResults() {
        return "/orders/viewResults";
    }

    @PostMapping("/results")
    public String getResults(@ModelAttribute("datePeriodForm") DatePeriodDTO datePeriodDTO, Model model) {
        model.addAttribute("totalCost", orderService.getTotalCost(datePeriodDTO));
        model.addAttribute("totalTickets", orderService.getTotalTickets(datePeriodDTO));
        return "/orders/viewResults";
    }



    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setSeatService(SeatService seatService) {
        this.seatService = seatService;
    }

    @Autowired
    public void setFilmSessionService(FilmSessionService filmSessionService) {
        this.filmSessionService = filmSessionService;
    }

    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }
}
