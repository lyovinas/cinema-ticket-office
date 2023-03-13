package ru.sbercourse.cinema.ticketoffice.garbage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sbercourse.cinema.ticketoffice.dto.FilmSessionDto;
import ru.sbercourse.cinema.ticketoffice.dto.GenericDto;
import ru.sbercourse.cinema.ticketoffice.dto.PlacementDto;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "DTO бронирования")
 class BookingDto extends GenericDto {

    @Schema(description = "Сеанс фильма")
    private FilmSessionDto filmSession;

    @Schema(description = "Место в кинозале")
    private PlacementDto placement;
}
