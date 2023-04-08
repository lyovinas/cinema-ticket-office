package ru.sbercourse.cinema.ticketoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "DTO киносеанса")
public class FilmSessionDTO extends GenericDTO {

    @Schema(description = "Идентификатор фильма")
    private Long filmId;

    @Schema(description = "Дата сеанса")
    private LocalDate startDate;

    @Schema(description = "Время начала")
    private LocalTime startTime;

    @Schema(description = "Цена")
    private double price;

}
