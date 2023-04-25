package ru.sbercourse.cinema.ticketoffice.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class LoginControllerTest extends CommonTest {

    @Test
    void loginWithoutAuth() throws Exception {
        mvc.perform(get("/login"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("users/login"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void loginWithAuth() throws Exception {
        mvc.perform(get("/login"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(redirectedUrlTemplate("/"));
    }
}
