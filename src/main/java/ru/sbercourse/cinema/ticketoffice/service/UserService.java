package ru.sbercourse.cinema.ticketoffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbercourse.cinema.ticketoffice.dto.UserDto;
import ru.sbercourse.cinema.ticketoffice.mapper.UserMapper;
import ru.sbercourse.cinema.ticketoffice.model.Role;
import ru.sbercourse.cinema.ticketoffice.model.User;
import ru.sbercourse.cinema.ticketoffice.repository.UserRepository;

@Service
public class UserService extends GenericService<User, UserDto> {

    private RoleService roleService;


    public UserService(UserRepository userRepository, UserMapper userMapper) {
        setRepository(userRepository);
        setMapper(userMapper);
    }


    @Override
    public UserDto create(UserDto userDto) {
        Role userRole = roleService.getByTitle("USER");
        if (userRole == null) {
            userRole = new Role();
            userRole.setTitle("USER");
            userRole.setDescription("Роль для пользователя фильмотеки");
            userRole = roleService.create(userRole);
        }
        userDto.setRole(userRole);
        return super.create(userDto);
    }



    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
