package ru.sbercourse.cinema.ticketoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO создателя фильма")
public class FilmCreatorDTO extends GenericDTO implements Comparable<FilmCreatorDTO> {

    @Schema(description = "Полное имя", example = "Роберт Земекис")
    private String fullName;

    @Schema(description = "Позиция при создании", example = "Режиссер")
    private String position;

    @Override
    public int compareTo(FilmCreatorDTO o) {
        return fullName.compareTo(o.fullName);
    }
}
