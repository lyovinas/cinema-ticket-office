package ru.sbercourse.cinema.ticketoffice.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;
import ru.sbercourse.cinema.ticketoffice.dto.FilmCreatorDTO;
import ru.sbercourse.cinema.ticketoffice.service.FilmCreatorService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
class FilmCreatorControllerTest extends CommonTest {

    private final FilmCreatorDTO testFilmCreatorDTO =
            new FilmCreatorDTO("MVC_Test FullName", "MVC_Test Position");
    private final Long existingFilmCreatorId = 1L;
    @Autowired
    private FilmCreatorService filmCreatorService;



    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void getAll() throws Exception {
        mvc.perform(get("/filmCreators"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("filmCreators/viewAllFilmCreators"))
                .andExpect(model().attributeExists("filmCreators"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void create() throws Exception {
        mvc.perform(get("/filmCreators/add"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("filmCreators/addFilmCreator"));

        mvc.perform(post("/filmCreators/add")
                        .flashAttr("filmCreatorForm", testFilmCreatorDTO)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/filmCreators"))
                .andExpect(redirectedUrlTemplate("/filmCreators"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void softDelete() throws Exception {
        filmCreatorService.restore(existingFilmCreatorId);
        FilmCreatorDTO filmCreatorForSoftDelete = filmCreatorService.getById(existingFilmCreatorId);
        assertFalse(filmCreatorForSoftDelete.isDeleted());

        mvc.perform(get("/filmCreators/delete/" + existingFilmCreatorId))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/filmCreators"))
                .andExpect(redirectedUrl("/filmCreators"));

        filmCreatorForSoftDelete = filmCreatorService.getById(existingFilmCreatorId);
        assertTrue(filmCreatorForSoftDelete.isDeleted());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void restore() throws Exception {
        filmCreatorService.softDelete(existingFilmCreatorId);
        FilmCreatorDTO filmCreatorForRestore = filmCreatorService.getById(existingFilmCreatorId);
        assertTrue(filmCreatorForRestore.isDeleted());

        mvc.perform(get("/filmCreators/restore/" + existingFilmCreatorId))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/filmCreators"))
                .andExpect(redirectedUrl("/filmCreators"));

        filmCreatorForRestore = filmCreatorService.getById(existingFilmCreatorId);
        assertFalse(filmCreatorForRestore.isDeleted());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void update() throws Exception {
        FilmCreatorDTO filmCreatorForUpdate = filmCreatorService.getById(existingFilmCreatorId);
        assertNotEquals("MVC_Test FullName_UPDATED", filmCreatorForUpdate.getFullName());
        filmCreatorForUpdate.setFullName("MVC_Test FullName_UPDATED");

        mvc.perform(get("/filmCreators/update/" + existingFilmCreatorId))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("filmCreators/updateFilmCreator"))
                .andExpect(model().attributeExists("filmCreator"));

        mvc.perform(post("/filmCreators/update")
                        .flashAttr("filmCreatorForm", filmCreatorForUpdate)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/filmCreators"))
                .andExpect(redirectedUrl("/filmCreators"));

        filmCreatorForUpdate = filmCreatorService.getById(existingFilmCreatorId);
        assertEquals("MVC_Test FullName_UPDATED", filmCreatorForUpdate.getFullName());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void search() throws Exception {
        FilmCreatorDTO filmCreator = filmCreatorService.getById(existingFilmCreatorId);

        mvc.perform(post("/filmCreators/search")
                        .flashAttr("filmCreatorSearchForm", filmCreator)
                        .with(csrf())
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("filmCreators/viewFoundFilmCreators"))
                .andExpect(model().attributeExists("filmCreators"));

        mvc.perform(post("/filmCreators/search")
                        .flashAttr("filmCreatorSearchForm", new FilmCreatorDTO("", ""))
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/filmCreators"))
                .andExpect(redirectedUrl("/filmCreators"));
    }
}
