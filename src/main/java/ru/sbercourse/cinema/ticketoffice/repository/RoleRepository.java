package ru.sbercourse.cinema.ticketoffice.repository;

import org.springframework.stereotype.Repository;
import ru.sbercourse.cinema.ticketoffice.model.Role;

@Repository
public interface RoleRepository extends GenericRepository<Role>{

    Role getByTitle(String title);
}
