package ru.sbercourse.cinema.ticketoffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbercourse.cinema.ticketoffice.dto.UserDTO;
import ru.sbercourse.cinema.ticketoffice.mapper.UserMapper;
import ru.sbercourse.cinema.ticketoffice.model.Order;
import ru.sbercourse.cinema.ticketoffice.model.Role;
import ru.sbercourse.cinema.ticketoffice.model.User;
import ru.sbercourse.cinema.ticketoffice.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.sbercourse.cinema.ticketoffice.constants.UserRolesConstants.MANAGER;
import static ru.sbercourse.cinema.ticketoffice.constants.UserRolesConstants.USER;

@Service
public class UserService extends GenericService<User, UserDTO> {

    private RoleService roleService;



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
        return super.create(userDTO);
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) {
        UserDTO foundUser = getById(userDTO.getId());

        userDTO.setCreatedWhen(foundUser.getCreatedWhen());
        userDTO.setCreatedBy(foundUser.getCreatedBy());
        userDTO.setDeleted(foundUser.isDeleted());
        userDTO.setDeletedWhen(foundUser.getDeletedWhen());
        userDTO.setDeletedBy(foundUser.getDeletedBy());
        userDTO.setUpdatedWhen(LocalDateTime.now());
        userDTO.setUpdatedBy(userDTO.getLogin());

        userDTO.setPassword(foundUser.getPassword());
        userDTO.setRole(foundUser.getRole());
        userDTO.setOrdersIds(foundUser.getOrdersIds());
//        userDTO.setReviews(foundUser.getReviews());

        return super.update(userDTO.getId(), userDTO);
    }

    public void createManager(UserDTO userDTO) {
        Role userRole = roleService.getByTitle(MANAGER);
        if (userRole == null) {
            userRole = roleService.create(new Role(MANAGER, "Управляющий"));
        }
        userDTO.setRole(userRole);
//        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        super.create(userDTO);
    }

    public User getByLogin(String login) {
        return ((UserRepository) repository).getByLogin(login);
    }

    public User getByEmail(String email) {
        return ((UserRepository) repository).getByEmail(email);
    }

    public List<Order> getOrders(Long userId) {
        User user = repository.findById(userId).orElse(null);
        return user != null
                ? user.getOrders().stream().toList()
                : null;
    }



    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
