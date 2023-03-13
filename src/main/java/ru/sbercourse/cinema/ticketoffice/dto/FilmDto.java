package ru.sbercourse.cinema.ticketoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "DTO фильма")
public class FilmDto extends GenericDto {

    @Schema(description = "Название фильма", example = "Назад в будущее")
    private String title;

    @Schema(description = "Год выхода фильма", example = "1985")
    private Short releaseYear;

    @Schema(description = "Страна производства фильма", example = "США")
    private String country;

    @Schema(description = "Жанр фильма", example = "Фантастика")
    private String genreText;

    @Schema(description = "Идентификаторы создателей фильма")
    private Set<Long> creatorsIds;

    @Schema(description = "Идентификаторы сеансов фильма", accessMode = Schema.AccessMode.READ_ONLY)
    private Set<Long> filmSessionsIds;
}
