package ru.sbercourse.cinema.ticketoffice.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercourse.cinema.ticketoffice.model.PlaceClass;
import ru.sbercourse.cinema.ticketoffice.repository.PlaceClassRepository;

@RestController
@RequestMapping("/placeClasses")
@Tag(name = "Категории мест", description = "Контроллер для работы с категориями мест в кинозале")
public class PlaceClassController extends GenericController<PlaceClass> {

    public PlaceClassController(PlaceClassRepository placeClassRepository) {
        setRepository(placeClassRepository);
    }
}
