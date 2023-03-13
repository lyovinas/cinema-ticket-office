package ru.sbercourse.cinema.ticketoffice.service;

import org.springframework.stereotype.Service;
import ru.sbercourse.cinema.ticketoffice.dto.PlaceClassDto;
import ru.sbercourse.cinema.ticketoffice.mapper.PlaceClassMapper;
import ru.sbercourse.cinema.ticketoffice.model.PlaceClass;
import ru.sbercourse.cinema.ticketoffice.repository.PlaceClassRepository;

@Service
public class PlaceClassService extends GenericService<PlaceClass, PlaceClassDto> {

    public PlaceClassService(PlaceClassRepository placeClassRepository, PlaceClassMapper placeClassMapper) {
        setRepository(placeClassRepository);
        setMapper(placeClassMapper);
    }
}
