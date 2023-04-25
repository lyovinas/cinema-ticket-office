package ru.sbercourse.cinema.ticketoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO киносеанса")
public class FilmSessionDTO extends GenericDTO implements Comparable<FilmSessionDTO> {

    @Schema(description = "Идентификатор фильма")
    private Long filmId;

    @Schema(description = "Дата сеанса")
    private LocalDate startDate;

    @Schema(description = "Время начала")
    private LocalTime startTime;

    @Schema(description = "Цена")
    private double price;

    @Override
    public int compareTo(FilmSessionDTO o) {
        if (startDate.isBefore(o.startDate) || (startDate.equals(o.startDate) && startTime.isBefore(o.startTime))) return -1;
        if (startDate.isAfter(o.startDate) || (startDate.equals(o.startDate) && startTime.isAfter(o.startTime))) return 1;
        return 0;
    }
}
