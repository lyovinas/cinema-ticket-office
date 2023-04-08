package ru.sbercourse.cinema.ticketoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "DTO отзыва")
public class ReviewDTO extends GenericDTO {

    @Schema(description = "Идентификатор фильма")
    private Long filmId;

    @Schema(description = "Идентификатор пользователя сервиса")
    private Long userId;

    @Schema(description = "Содержание отзыва")
    private String content;
}
