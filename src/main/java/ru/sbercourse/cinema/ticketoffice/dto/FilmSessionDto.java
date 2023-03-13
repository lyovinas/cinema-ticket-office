package ru.sbercourse.cinema.ticketoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "DTO киносеанса")
public class FilmSessionDto extends GenericDto {

    @Schema(description = "Идентификатор фильма")
    private Long filmId;

    @Schema(description = "Дата/время начала")
    private LocalDateTime start;

    @Schema(description = "Цена")
    private double price;

//    @Schema(description = "Идентификаторы заказов", accessMode = Schema.AccessMode.READ_ONLY)
//    private Set<Long> ordersIds;
}
