package ru.sbercourse.cinema.ticketoffice.service;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.sbercourse.cinema.ticketoffice.dto.GenericDTO;
import ru.sbercourse.cinema.ticketoffice.mapper.Mapper;
import ru.sbercourse.cinema.ticketoffice.model.GenericModel;
import ru.sbercourse.cinema.ticketoffice.repository.GenericRepository;
import ru.sbercourse.cinema.ticketoffice.service.userdetails.CustomUserDetails;

abstract class GenericServiceTest<E extends GenericModel, D extends GenericDTO> {

    protected GenericRepository<E> repository;
    protected Mapper<E, D> mapper;
    protected GenericService<E, D> service;

    @BeforeEach
    void init() {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                CustomUserDetails.builder()
                        .username("USER"),
                null,
                null
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    abstract void getAll();
    abstract void getById();
    abstract void create();
    abstract void update();
    abstract void delete();
    abstract void softDelete();
    abstract void restore();
}
