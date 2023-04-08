package ru.sbercourse.cinema.ticketoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "DTO для добавления создателя к фильму")
public class AddFilmCreatorDTO {

    @Schema(description = "Идентификатор фильма")
    Long filmId;

    @Schema(description = "Идентификатор создателя")
    Long filmCreatorId;
}
