package ru.sbercourse.cinema.ticketoffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sbercourse.cinema.ticketoffice.constants.MailConstants;
import ru.sbercourse.cinema.ticketoffice.dto.UserDTO;
import ru.sbercourse.cinema.ticketoffice.mapper.UserMapper;
import ru.sbercourse.cinema.ticketoffice.model.Order;
import ru.sbercourse.cinema.ticketoffice.model.Role;
import ru.sbercourse.cinema.ticketoffice.model.User;
import ru.sbercourse.cinema.ticketoffice.repository.UserRepository;
import ru.sbercourse.cinema.ticketoffice.utils.MailUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static ru.sbercourse.cinema.ticketoffice.constants.UserRolesConstants.MANAGER;
import static ru.sbercourse.cinema.ticketoffice.constants.UserRolesConstants.USER;

@Service
public class UserService extends GenericService<User, UserDTO> {

    private RoleService roleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String mailServerUsername;



    public UserService(UserRepository userRepository, UserMapper userMapper) {
        repository = userRepository;
        mapper = userMapper;
    }



    @Override
    public UserDTO create(UserDTO userDTO) {
        Role userRole = roleService.getByTitle(USER);
        if (userRole == null) {
            userRole = roleService.create(new Role(USER, "Пользователь"));
        }
        userDTO.setRole(userRole);
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        return super.create(userDTO);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        User foundUser = repository.findById(userDTO.getId()).orElse(null);

        if (foundUser != null) {
            foundUser.setFirstName(userDTO.getFirstName());
            foundUser.setLastName(userDTO.getLastName());
            foundUser.setBirthDate(userDTO.getBirthDate());
            foundUser.setEmail(userDTO.getEmail());
            foundUser.setLogin(userDTO.getLogin());
            foundUser.setUpdatedWhen(LocalDateTime.now());
            foundUser.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            return mapper.toDTO(repository.save(foundUser));
        }

        return null;
    }

    public void createManager(UserDTO userDTO) {
        Role userRole = roleService.getByTitle(MANAGER);
        if (userRole == null) {
            userRole = roleService.create(new Role(MANAGER, "Управляющий"));
        }
        userDTO.setRole(userRole);
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        super.create(userDTO);
    }

    public UserDTO getByLogin(String login) {
        return mapper.toDTO(((UserRepository) repository).getByLogin(login));
    }

    public UserDTO getByEmail(String email) {
        return mapper.toDTO(((UserRepository) repository).getByEmail(email));
    }

    public List<Order> getOrders(Long userId) {
        User user = repository.findById(userId).orElse(null);
        return user != null
                ? user.getOrders().stream().toList()
                : null;
    }

    public void sendChangePasswordEmail(String email) {
        User user = mapper.toEntity(getByEmail(email));
        UUID uuid = UUID.randomUUID();
        user.setChangePasswordToken(uuid.toString());
        user.setUpdatedWhen(LocalDateTime.now());
        user.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        repository.save(user);
        SimpleMailMessage message = MailUtils.createEmailMessage(
                mailServerUsername,
                user.getEmail(),
                MailConstants.MAIL_SUBJECT_FOR_REMEMBER_PASSWORD,
                MailConstants.MAIL_MESSAGE_FOR_REMEMBER_PASSWORD +
                        "http://localhost:8080/users/change-password?uuid=" + uuid
        );
        javaMailSender.send(message);
    }

    public void changePassword(String uuid, String password) {
        User user = ((UserRepository) repository).getByChangePasswordToken(uuid);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setChangePasswordToken(null);
        user.setUpdatedWhen(LocalDateTime.now());
        user.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        repository.save(user);
    }



    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
}
