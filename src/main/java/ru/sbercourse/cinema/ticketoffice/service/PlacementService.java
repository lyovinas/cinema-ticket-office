package ru.sbercourse.cinema.ticketoffice.service;

import org.springframework.stereotype.Service;
import ru.sbercourse.cinema.ticketoffice.dto.PlacementDto;
import ru.sbercourse.cinema.ticketoffice.mapper.PlacementMapper;
import ru.sbercourse.cinema.ticketoffice.model.Placement;
import ru.sbercourse.cinema.ticketoffice.repository.PlacementRepository;

@Service
public class PlacementService extends GenericService<Placement, PlacementDto> {

    public PlacementService(PlacementRepository placementRepository, PlacementMapper placementMapper) {
        setRepository(placementRepository);
        setMapper(placementMapper);
    }
}
