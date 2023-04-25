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
@Schema(description = "DTO отзыва")
public class ReviewDTO extends GenericDTO implements Comparable<ReviewDTO> {

    @Schema(description = "Идентификатор фильма")
    private Long filmId;

    @Schema(description = "Пользователь сервиса")
    private UserDTO userDTO;

    @Schema(description = "Содержание отзыва")
    private String content;

    @Override
    public int compareTo(ReviewDTO o) {
        return createdWhen.compareTo(o.createdWhen);
    }
}
