package ru.sbercourse.cinema.ticketoffice.repository;

import org.springframework.stereotype.Repository;
import ru.sbercourse.cinema.ticketoffice.model.User;

@Repository
public interface UserRepository extends GenericRepository<User> {

    User getByLogin(String login);

    User getByEmail(String email);

    User getByChangePasswordToken(String uuid);
}
