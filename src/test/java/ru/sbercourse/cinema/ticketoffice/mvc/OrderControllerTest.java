package ru.sbercourse.cinema.ticketoffice.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.transaction.annotation.Transactional;
import ru.sbercourse.cinema.ticketoffice.dto.DatePeriodDTO;
import ru.sbercourse.cinema.ticketoffice.dto.OrderDTO;
import ru.sbercourse.cinema.ticketoffice.service.OrderService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
class OrderControllerTest extends CommonTest {

    private final Long EXISTING_ORDER_ID = 1L;
    private final Long EXISTING_USER_ID = 1L;
    private final String EXISTING_USER_LOGIN = "u";
    private final Long EXISTING_FILMSESSION_ID = 1L;
    private final OrderDTO TEST_ORDER_DTO =
            new OrderDTO(EXISTING_USER_ID, EXISTING_FILMSESSION_ID, new HashSet<>(List.of(1L)), 0, false);
    @Autowired
    OrderService orderService;



    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void getAll() throws Exception {
        mvc.perform(get("/orders"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("orders/viewAllOrders"))
                .andExpect(model().attributeExists("orders"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void getById() throws Exception {
        mvc.perform(get("/orders/get/" + EXISTING_ORDER_ID))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("orders/viewOrder"))
                .andExpect(model().attributeExists("order"))
                .andExpect(model().attributeExists("orderForm"))
                .andExpect(model().attributeExists("filmTitle"))
                .andExpect(model().attributeExists("filmSession"))
                .andExpect(model().attributeExists("seats"))
                .andExpect(model().attributeExists("canPurchased"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void getAllInfoByUserId() throws Exception {
        mvc.perform(get("/orders/user/" + EXISTING_USER_ID))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("orders/viewAllUserOrders"))
                .andExpect(model().attributeExists("orders"))
                .andExpect(model().attributeExists("userId"));
    }

    @Test
    @WithUserDetails(EXISTING_USER_LOGIN)
    void previewOrder() throws Exception {
        mvc.perform(post("/orders/preview")
                        .flashAttr("orderForm", new OrderDTO())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:" + null))
                .andExpect(redirectedUrl("null"));

        mvc.perform(post("/orders/preview")
                        .flashAttr("orderForm", TEST_ORDER_DTO)
                        .with(csrf()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("orders/preview"))
                .andExpect(model().attributeExists("filmTitle"))
                .andExpect(model().attributeExists("filmSession"))
                .andExpect(model().attributeExists("seats"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void addOrder() throws Exception {
        mvc.perform(post("/orders/add")
                        .flashAttr("orderForm", TEST_ORDER_DTO)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/orders/user/" + TEST_ORDER_DTO.getUserId()))
                .andExpect(redirectedUrl("/orders/user/" + TEST_ORDER_DTO.getUserId()));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void purchase() throws Exception {
        OrderDTO existingOrderDTO = orderService.getById(EXISTING_ORDER_ID);
        if (existingOrderDTO.isPurchase()) {
            existingOrderDTO.setPurchase(false);
            orderService.update(existingOrderDTO);
            existingOrderDTO = orderService.getById(EXISTING_ORDER_ID);
        }
        assertFalse(existingOrderDTO.isPurchase());

        mvc.perform(post("/orders/purchase")
                        .flashAttr("orderForm", existingOrderDTO)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/orders/user/" + existingOrderDTO.getUserId()))
                .andExpect(redirectedUrl("/orders/user/" + existingOrderDTO.getUserId()));

        existingOrderDTO = orderService.getById(EXISTING_ORDER_ID);
        assertTrue(existingOrderDTO.isPurchase());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void softDelete() throws Exception {
        orderService.restore(EXISTING_ORDER_ID);
        OrderDTO orderForSoftDelete = orderService.getById(EXISTING_ORDER_ID);
        assertFalse(orderForSoftDelete.isDeleted());

        mvc.perform(get("/orders/delete/" + EXISTING_ORDER_ID))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:" + null))
                .andExpect(redirectedUrl("null"));

        orderForSoftDelete = orderService.getById(EXISTING_ORDER_ID);
        assertTrue(orderForSoftDelete.isDeleted());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void restore() throws Exception {
        orderService.softDelete(EXISTING_ORDER_ID);
        OrderDTO orderForRestore = orderService.getById(EXISTING_ORDER_ID);
        assertTrue(orderForRestore.isDeleted());

        mvc.perform(get("/orders/restore/" + EXISTING_ORDER_ID))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/orders"))
                .andExpect(redirectedUrl("/orders"));

        orderForRestore = orderService.getById(EXISTING_ORDER_ID);
        assertFalse(orderForRestore.isDeleted());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void getResults() throws Exception {
        mvc.perform(get("/orders/results"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("orders/viewResults"));

        mvc.perform(post("/orders/results")
                        .flashAttr("datePeriodForm", new DatePeriodDTO(LocalDate.now(), LocalDate.now()))
                        .with(csrf()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("orders/viewResults"))
                .andExpect(model().attributeExists("totalTickets"));
    }
}
