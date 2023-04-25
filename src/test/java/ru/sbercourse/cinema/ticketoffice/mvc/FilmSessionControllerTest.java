package ru.sbercourse.cinema.ticketoffice.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;
import ru.sbercourse.cinema.ticketoffice.dto.FilmSessionDTO;
import ru.sbercourse.cinema.ticketoffice.service.FilmSessionService;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
class FilmSessionControllerTest extends CommonTest {

    private final Long existingFilmId = 1L;
    private final FilmSessionDTO testFilmSessionDTO =
            new FilmSessionDTO(existingFilmId, LocalDate.now(), LocalTime.now(), 0);
    private final Long existingFilmSessionId = 1L;
    @Autowired
    private FilmSessionService filmSessionService;


    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void create() throws Exception {
        mvc.perform(post("/filmSessions/add")
                        .flashAttr("filmSessionForm", testFilmSessionDTO)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/films/get/" + testFilmSessionDTO.getFilmId()))
                .andExpect(redirectedUrlTemplate("/films/get/" + testFilmSessionDTO.getFilmId()));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void softDelete() throws Exception {
        filmSessionService.restore(existingFilmSessionId);
        FilmSessionDTO filmSessionForSoftDelete = filmSessionService.getById(existingFilmSessionId);
        assertFalse(filmSessionForSoftDelete.isDeleted());

        mvc.perform(get("/filmSessions/delete/" + existingFilmSessionId))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:" + null))
                .andExpect(redirectedUrl("null"));

        filmSessionForSoftDelete = filmSessionService.getById(existingFilmSessionId);
        assertTrue(filmSessionForSoftDelete.isDeleted());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void restore() throws Exception {
        filmSessionService.softDelete(existingFilmSessionId);
        FilmSessionDTO filmSessionForRestore = filmSessionService.getById(existingFilmSessionId);
        assertTrue(filmSessionForRestore.isDeleted());

        mvc.perform(get("/filmSessions/restore/" + existingFilmSessionId))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:" + null))
                .andExpect(redirectedUrl("null"));

        filmSessionForRestore = filmSessionService.getById(existingFilmSessionId);
        assertFalse(filmSessionForRestore.isDeleted());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void update() throws Exception {
        FilmSessionDTO filmSessionForUpdate = filmSessionService.getById(existingFilmSessionId);
        assertNotEquals(LocalDate.of(9999, 1, 1), filmSessionForUpdate.getStartDate());
        filmSessionForUpdate.setStartDate(LocalDate.of(9999, 1, 1));

        mvc.perform(get("/filmSessions/update/" + existingFilmSessionId))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("filmSessions/updateFilmSession"))
                .andExpect(model().attributeExists("filmSession"))
                .andExpect(model().attributeExists("filmTitle"));

        mvc.perform(post("/filmSessions/update")
                        .flashAttr("filmSessionForm", filmSessionForUpdate)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/films/get/" + filmSessionForUpdate.getFilmId()))
                .andExpect(redirectedUrl("/films/get/" + filmSessionForUpdate.getFilmId()));

        filmSessionForUpdate = filmSessionService.getById(existingFilmSessionId);
        assertEquals(LocalDate.of(9999, 1, 1), filmSessionForUpdate.getStartDate());
    }
}
