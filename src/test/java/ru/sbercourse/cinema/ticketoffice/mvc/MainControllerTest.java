package ru.sbercourse.cinema.ticketoffice.mvc;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MainControllerTest extends CommonTest {

    @Test
    void index() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("films"))
                .andExpect(model().attributeExists("today"))
                .andExpect(model().attributeExists("tomorrow"));

        mvc.perform(post("/")
                        .flashAttr("startDate", LocalDate.now())
                        .with(csrf()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("films"))
                .andExpect(model().attributeExists("today"))
                .andExpect(model().attributeExists("tomorrow"));
    }
}
