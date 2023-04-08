package ru.sbercourse.cinema.ticketoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "DTO создателя фильма")
public class FilmCreatorDTO extends GenericDTO {

    @Schema(description = "Полное имя", example = "Роберт Земекис")
    private String fullName;

    @Schema(description = "Позиция при создании", example = "Режиссер")
    private String position;

}
