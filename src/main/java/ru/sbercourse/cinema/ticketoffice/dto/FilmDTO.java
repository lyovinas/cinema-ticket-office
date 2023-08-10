package ru.sbercourse.cinema.ticketoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sbercourse.cinema.ticketoffice.model.Genre;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO фильма")
public class FilmDTO extends GenericDTO {

    @Schema(description = "Название фильма", example = "Назад в будущее")
    private String title;

    @Schema(description = "Год выхода фильма", example = "1985")
    private Short releaseYear;

    @Schema(description = "Страна производства фильма", example = "США")
    private String country;

    @Schema(description = "Жанр фильма", example = "FANTASY")
    private Genre genre;

    @Schema(description = "Описание фильма", example = "Подросток Марти с помощью машины времени...")
    private String description;

    @Schema(description = "Имя файла с постером", example = "1.jpg")
    private String posterFileName;

    @Schema(description = "Рейтинг Кинопоиска", example = "8.314")
    private Float ratingKp;
}
