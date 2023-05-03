package ru.sbercourse.cinema.ticketoffice.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.transaction.annotation.Transactional;
import ru.sbercourse.cinema.ticketoffice.dto.UserDTO;
import ru.sbercourse.cinema.ticketoffice.mapper.UserMapper;
import ru.sbercourse.cinema.ticketoffice.model.Role;
import ru.sbercourse.cinema.ticketoffice.model.User;
import ru.sbercourse.cinema.ticketoffice.repository.UserRepository;
import ru.sbercourse.cinema.ticketoffice.service.UserService;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
class UserControllerTest extends CommonTest {

    private final String BASE_URL = "/users";
    private final String EXISTING_USER_LOGIN = "u";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void getAll() throws Exception {
        mvc.perform(get(BASE_URL))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("users/viewAllUsers"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    @WithUserDetails(EXISTING_USER_LOGIN)
    void viewProfile() throws Exception {
        Long existingUserId = userService.getByLogin(EXISTING_USER_LOGIN).getId();
        mvc.perform(get(BASE_URL + "/profile/" + existingUserId))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("users/viewProfile"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    @WithUserDetails(EXISTING_USER_LOGIN)
    void updateProfile() throws Exception {
        UserDTO existingUserDTO = userService.getByLogin(EXISTING_USER_LOGIN);
        Long existingUserId = existingUserDTO.getId();

        mvc.perform(get(BASE_URL + "/profile/update/" + existingUserId))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("users/updateProfile"));

        assertNotEquals("MVC_Test FirstName_UPDATED", existingUserDTO.getFirstName());
        existingUserDTO.setFirstName("MVC_Test FirstName_UPDATED");

        mvc.perform(post(BASE_URL + "/profile/update")
                        .flashAttr("userForm", existingUserDTO)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:"+ BASE_URL + "/profile/" + existingUserDTO.getId()))
                .andExpect(redirectedUrl(BASE_URL + "/profile/" + existingUserDTO.getId()));

        existingUserDTO = userService.getByLogin(EXISTING_USER_LOGIN);
        assertEquals("MVC_Test FirstName_UPDATED", existingUserDTO.getFirstName());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void registration() throws Exception {
        mvc.perform(get(BASE_URL + "/registration"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("users/registration"))
                .andExpect(model().attributeExists("userForm"));

        UserDTO userDTO = new UserDTO("", "", LocalDate.now(), "",
                EXISTING_USER_LOGIN, "", "", new Role(), new HashSet<>());
        mvc.perform(post(BASE_URL + "/registration")
                        .flashAttr("userForm", userDTO)
                        .with(csrf()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("users/registration"));

        userDTO.setLogin("MVC_Test Login");
        userDTO.setEmail(userService.getByLogin(EXISTING_USER_LOGIN).getEmail());
        mvc.perform(post(BASE_URL + "/registration")
                        .flashAttr("userForm", userDTO)
                        .with(csrf()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("users/registration"));

        userDTO.setEmail("MVC_Test Email");
        mvc.perform(post(BASE_URL + "/registration")
                        .flashAttr("userForm", userDTO)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"))
                .andExpect(redirectedUrlTemplate("/login"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void addManager() throws Exception {
        mvc.perform(get(BASE_URL + "/add-manager"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("users/addManager"))
                .andExpect(model().attributeExists("userForm"));

        UserDTO userDTO = new UserDTO("", "", LocalDate.now(), "",
                EXISTING_USER_LOGIN, "", "", new Role(), new HashSet<>());
        mvc.perform(post(BASE_URL + "/add-manager")
                        .flashAttr("userForm", userDTO)
                        .with(csrf()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("users/addManager"));

        userDTO.setLogin("MVC_Test Login");
        userDTO.setEmail(userService.getByLogin(EXISTING_USER_LOGIN).getEmail());
        mvc.perform(post(BASE_URL + "/add-manager")
                        .flashAttr("userForm", userDTO)
                        .with(csrf()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("users/addManager"));

        userDTO.setEmail("MVC_Test Email");
        mvc.perform(post(BASE_URL + "/add-manager")
                        .flashAttr("userForm", userDTO)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:" + BASE_URL))
                .andExpect(redirectedUrlTemplate(BASE_URL));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void rememberPassword() throws Exception {
        mvc.perform(get(BASE_URL + "/remember-password"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("users/rememberPassword"));

//        UserDTO existingUserDTO = userService.getByLogin(EXISTING_USER_LOGIN);
//        mvc.perform(post(BASE_URL + "/remember-password")
//                        .flashAttr("changePasswordForm", existingUserDTO)
//                        .with(csrf()))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/login"))
//                .andExpect(redirectedUrl("/login"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void changePassword() throws Exception {
        mvc.perform(get(BASE_URL + "/change-password")
                        .param("uuid", "MVC_Test uuid"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("users/changePassword"));

        UserDTO existingUserDTO = userService.getByLogin(EXISTING_USER_LOGIN);
        User user = userMapper.toEntity(existingUserDTO);
        user.setChangePasswordToken("MVC_Test uuid");
        userRepository.save(user);
        assertFalse(bCryptPasswordEncoder.matches("MVC_Test newPassword", existingUserDTO.getPassword()));
        existingUserDTO.setPassword("MVC_Test newPassword");

        mvc.perform(post(BASE_URL + "/change-password")
                        .param("uuid", "MVC_Test uuid")
                        .flashAttr("changePasswordForm", existingUserDTO)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"))
                .andExpect(redirectedUrl("/login"));

        existingUserDTO = userService.getByLogin(EXISTING_USER_LOGIN);
        assertTrue(bCryptPasswordEncoder.matches("MVC_Test newPassword", existingUserDTO.getPassword()));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void softDelete() throws Exception {
        Long existingUserId = userService.getByLogin(EXISTING_USER_LOGIN).getId();
        userService.restore(existingUserId);
        UserDTO userForSoftDelete = userService.getById(existingUserId);
        assertFalse(userForSoftDelete.isDeleted());

        mvc.perform(get(BASE_URL + "/delete/" + existingUserId))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:" + BASE_URL))
                .andExpect(redirectedUrl(BASE_URL));

        userForSoftDelete = userService.getById(existingUserId);
        assertTrue(userForSoftDelete.isDeleted());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    void restore() throws Exception {
        Long existingUserId = userService.getByLogin(EXISTING_USER_LOGIN).getId();
        userService.softDelete(existingUserId);
        UserDTO userForRestore = userService.getById(existingUserId);
        assertTrue(userForRestore.isDeleted());

        mvc.perform(get(BASE_URL + "/restore/" + existingUserId))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:" + BASE_URL))
                .andExpect(redirectedUrl(BASE_URL));

        userForRestore = userService.getById(existingUserId);
        assertFalse(userForRestore.isDeleted());
    }
}
