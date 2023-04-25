package ru.sbercourse.cinema.ticketoffice.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;
import ru.sbercourse.cinema.ticketoffice.dto.SeatDTO;
import ru.sbercourse.cinema.ticketoffice.service.SeatService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
class SeatControllerTest extends CommonTest {

    private final String BASE_URL = "/seats";
    private final Long EXISTING_SEAT_ID = 1L;
    @Autowired
    private SeatService seatService;



    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void getAll() throws Exception {
        mvc.perform(get(BASE_URL))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("seats/viewAllSeats"))
                .andExpect(model().attributeExists("seats"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void create() throws Exception {
        mvc.perform(post(BASE_URL + "/add")
                        .flashAttr("seatForm", new SeatDTO((byte) 0, (byte) 0))
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:" + BASE_URL))
                .andExpect(redirectedUrl(BASE_URL));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void softDelete() throws Exception {
        seatService.restore(EXISTING_SEAT_ID);
        SeatDTO seatForSoftDelete = seatService.getById(EXISTING_SEAT_ID);
        assertFalse(seatForSoftDelete.isDeleted());

        mvc.perform(get(BASE_URL + "/delete/" + EXISTING_SEAT_ID))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:" + BASE_URL))
                .andExpect(redirectedUrl(BASE_URL));

        seatForSoftDelete = seatService.getById(EXISTING_SEAT_ID);
        assertTrue(seatForSoftDelete.isDeleted());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void restore() throws Exception {
        seatService.softDelete(EXISTING_SEAT_ID);
        SeatDTO seatForRestore = seatService.getById(EXISTING_SEAT_ID);
        assertTrue(seatForRestore.isDeleted());

        mvc.perform(get(BASE_URL + "/restore/" + EXISTING_SEAT_ID))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:" + BASE_URL))
                .andExpect(redirectedUrl(BASE_URL));

        seatForRestore = seatService.getById(EXISTING_SEAT_ID);
        assertFalse(seatForRestore.isDeleted());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void update() throws Exception {
        SeatDTO seatForUpdate = seatService.getById(EXISTING_SEAT_ID);
        assertNotEquals(0, (byte) seatForUpdate.getRow());
        seatForUpdate.setRow((byte) 0);

        mvc.perform(get(BASE_URL + "/update/" + EXISTING_SEAT_ID))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("seats/updateSeat"))
                .andExpect(model().attributeExists("seat"));

        mvc.perform(post(BASE_URL + "/update")
                        .flashAttr("seatForm", seatForUpdate)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:" + BASE_URL))
                .andExpect(redirectedUrl(BASE_URL));

        seatForUpdate = seatService.getById(EXISTING_SEAT_ID);
        assertEquals(0, (byte) seatForUpdate.getRow());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void search() throws Exception {
        SeatDTO existingSeatDTO = seatService.getById(EXISTING_SEAT_ID);

        mvc.perform(post(BASE_URL + "/search")
                        .flashAttr("seatsSearchForm", existingSeatDTO)
                        .with(csrf())
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("seats/viewAllSeats"))
                .andExpect(model().attributeExists("seats"));

        mvc.perform(post(BASE_URL + "/search")
                        .flashAttr("seatsSearchForm", new SeatDTO(null, null))
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:" + BASE_URL))
                .andExpect(redirectedUrl(BASE_URL));
    }

    @Test
    void getSeats() throws Exception {
        Long existingFilmSessionId = 1L;
        mvc.perform(get(BASE_URL + "/selectSeat")
                        .param("filmSessionId", String.valueOf(existingFilmSessionId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("seats/selectSeat"))
                .andExpect(model().attributeExists("filmTitle"))
                .andExpect(model().attributeExists("filmSession"))
                .andExpect(model().attributeExists("seatsInMap"));
    }
}
