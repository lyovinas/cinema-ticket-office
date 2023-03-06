package ru.sbercourse.cinema.ticketoffice.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercourse.cinema.ticketoffice.model.Placement;
import ru.sbercourse.cinema.ticketoffice.repository.PlacementRepository;

@RestController
@RequestMapping("/placements")
@Tag(name = "Места", description = "Контроллер для работы с местами в кинозале")
public class PlacementController extends GenericController<Placement> {

    public PlacementController(PlacementRepository placementRepository) {
        setRepository(placementRepository);
    }
}
