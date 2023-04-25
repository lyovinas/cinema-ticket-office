package ru.sbercourse.cinema.ticketoffice.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;
import ru.sbercourse.cinema.ticketoffice.dto.AddFilmCreatorDTO;
import ru.sbercourse.cinema.ticketoffice.dto.FilmCreatorDTO;
import ru.sbercourse.cinema.ticketoffice.dto.FilmDTO;
import ru.sbercourse.cinema.ticketoffice.dto.FilmSearchDTO;
import ru.sbercourse.cinema.ticketoffice.model.Genre;
import ru.sbercourse.cinema.ticketoffice.repository.FilmRepository;
import ru.sbercourse.cinema.ticketoffice.service.FilmService;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
class FilmControllerTest extends CommonTest {

    private final FilmDTO testFilmDTO = new FilmDTO("MVC_Test Title", (short) 0,
            "MVC_Test Country", Genre.ACTION, "", null);
    private final FilmSearchDTO testFilmSearchDTO =
            new FilmSearchDTO(testFilmDTO.getTitle(), null, null);
    private final Long existingFilmId = 6L;
    private final Long existingFilmCreatorId = 1L;
    @Autowired
    FilmService filmService;
    @Autowired
    FilmRepository filmRepository;
    @Value("${posters.path}")
    private String postersPath;



    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void getAll() throws Exception {
        mvc.perform(get("/films"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("films/viewAllFilms"))
                .andExpect(model().attributeExists("films"));
    }

    @Test
    void viewFilm() throws Exception {
        mvc.perform(get("/films/get/" + existingFilmId))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("films/viewFilm"))
                .andExpect(model().attributeExists("film"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void create() throws Exception {
        mvc.perform(get("/films/add"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("films/addFilm"));

        MockMultipartFile fileEmpty = new MockMultipartFile("file", InputStream.nullInputStream());
        mvc.perform(multipart(HttpMethod.POST, "/films/add")
                        .file(fileEmpty)
                        .flashAttr("filmForm", testFilmDTO)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/films"))
                .andExpect(redirectedUrlTemplate("/films"));

        testFilmDTO.setTitle("MVC_Test Title with file");
        MockMultipartFile fileNotEmpty = new MockMultipartFile("file", "test".getBytes());
        mvc.perform(multipart(HttpMethod.POST, "/films/add")
                        .file(fileNotEmpty)
                        .flashAttr("filmForm", testFilmDTO)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/films"))
                .andExpect(redirectedUrlTemplate("/films"));

        Long createdFilmId = filmRepository.getByTitle(testFilmDTO.getTitle()).get(0).getId();
        Path pathToCreatedFile = Paths.get(postersPath + "/" + createdFilmId).toAbsolutePath();
        Files.deleteIfExists(pathToCreatedFile);
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void softDelete() throws Exception {
        filmService.restore(existingFilmId);
        FilmDTO foundFilmForSoftDelete = filmService.getById(existingFilmId);
        assertFalse(foundFilmForSoftDelete.isDeleted());

        mvc.perform(get("/films/delete/" + existingFilmId))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/films"))
                .andExpect(redirectedUrl("/films"));

        foundFilmForSoftDelete = filmService.getById(existingFilmId);
        assertTrue(foundFilmForSoftDelete.isDeleted());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void restore() throws Exception {
        filmService.softDelete(existingFilmId);
        FilmDTO foundFilmForRestore = filmService.getById(existingFilmId);
        assertTrue(foundFilmForRestore.isDeleted());

        mvc.perform(get("/films/restore/" + existingFilmId))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/films"))
                .andExpect(redirectedUrl("/films"));

        foundFilmForRestore = filmService.getById(existingFilmId);
        assertFalse(foundFilmForRestore.isDeleted());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void update() throws Exception {
        mvc.perform(get("/films/update/" + existingFilmId))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("films/updateFilm"))
                .andExpect(model().attributeExists("film"))
                .andExpect(model().attributeExists("filmCreators"));

        FilmDTO filmDTO = filmService.getById(existingFilmId);
        assertNotEquals("MVC_Test UPDATED_Title", filmDTO.getTitle());
        filmDTO.setTitle("MVC_Test UPDATED_Title");
        MockMultipartFile fileEmpty = new MockMultipartFile("file", InputStream.nullInputStream());
        mvc.perform(multipart(HttpMethod.POST, "/films/update")
                        .file(fileEmpty)
                        .flashAttr("filmForm", filmDTO)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/films/get/" + existingFilmId))
                .andExpect(redirectedUrlTemplate("/films/get/" + existingFilmId));
        filmDTO = filmService.getById(existingFilmId);
        assertEquals("MVC_Test UPDATED_Title", filmDTO.getTitle());

        MockMultipartFile fileNotEmpty = new MockMultipartFile("file", "test".getBytes());
        boolean needToDelete = true;
        String existingFilmPosterPath = filmService.getById(existingFilmId).getPosterFileName();
        if (existingFilmPosterPath != null && !existingFilmPosterPath.isBlank()) {
            String fileName = existingFilmPosterPath.split("/")[existingFilmPosterPath.split("/").length - 1];
            Path path = Paths.get(postersPath + "/" + fileName).toAbsolutePath();
            if (!fileName.isBlank() && path.toFile().exists()) {
                fileNotEmpty = new MockMultipartFile("file", fileName, MediaType.ALL_VALUE, new FileInputStream(path.toFile()));
                needToDelete = false;
            }
        }
        mvc.perform(multipart(HttpMethod.POST, "/films/update")
                        .file(fileNotEmpty)
                        .flashAttr("filmForm", filmDTO)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/films/get/" + existingFilmId))
                .andExpect(redirectedUrlTemplate("/films/get/" + existingFilmId));

        if (needToDelete) {
            Path pathToCreatedFile = Paths.get(postersPath + "/" + existingFilmId).toAbsolutePath();
            Files.deleteIfExists(pathToCreatedFile);
        }
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void addFilmCreator() throws Exception {
        mvc.perform(get("/films/" + existingFilmId + "/addFilmCreator"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("films/addFilmCreator"))
                .andExpect(model().attributeExists("film"))
                .andExpect(model().attributeExists("filmCreators"));

        List<Long> filmCreatorsIds = filmService.getFilmCreators(existingFilmId).stream().map(FilmCreatorDTO::getId).toList();
        if (filmCreatorsIds.contains(existingFilmCreatorId)) {
            filmService.deleteFilmCreator(existingFilmId, existingFilmCreatorId);
            filmCreatorsIds = filmService.getFilmCreators(existingFilmId).stream().map(FilmCreatorDTO::getId).toList();
        }
        assertFalse(filmCreatorsIds.contains(existingFilmCreatorId));

        AddFilmCreatorDTO addFilmCreatorDTO = new AddFilmCreatorDTO(existingFilmId, existingFilmCreatorId);
        mvc.perform(post("/films/addFilmCreator")
                        .flashAttr("filmCreatorAddForm", addFilmCreatorDTO)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/films/update/" + existingFilmId))
                .andExpect(redirectedUrl("/films/update/" + existingFilmId));
        filmCreatorsIds = filmService.getFilmCreators(existingFilmId).stream().map(FilmCreatorDTO::getId).toList();
        assertTrue(filmCreatorsIds.contains(existingFilmCreatorId));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void deleteFilmCreator() throws Exception {
        List<Long> filmCreatorsIds = filmService.getFilmCreators(existingFilmId).stream().map(FilmCreatorDTO::getId).toList();
        if (!filmCreatorsIds.contains(existingFilmCreatorId)) {
            filmService.addFilmCreator(existingFilmId, existingFilmCreatorId);
            filmCreatorsIds = filmService.getFilmCreators(existingFilmId).stream().map(FilmCreatorDTO::getId).toList();
        }
        assertTrue(filmCreatorsIds.contains(existingFilmCreatorId));

        mvc.perform(get("/films/" + existingFilmId + "/deleteFilmCreator/" + existingFilmCreatorId))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/films/update/" + existingFilmId))
                .andExpect(redirectedUrl("/films/update/" + existingFilmId));

        filmCreatorsIds = filmService.getFilmCreators(existingFilmId).stream().map(FilmCreatorDTO::getId).toList();
        assertFalse(filmCreatorsIds.contains(existingFilmCreatorId));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void search() throws Exception {
        mvc.perform(post("/films/search")
                        .flashAttr("filmSearchForm", testFilmSearchDTO)
                        .with(csrf()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("films/viewFoundFilms"))
                .andExpect(model().attributeExists("films"));
    }
}
