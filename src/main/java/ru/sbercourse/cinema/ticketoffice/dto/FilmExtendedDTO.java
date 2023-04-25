package ru.sbercourse.cinema.ticketoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.TreeSet;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "DTO фильма с создателями, сеансами и отзывами")
public class FilmExtendedDTO extends FilmDTO {

    @Schema(description = "Создатели фильма")
    private TreeSet<FilmCreatorDTO> filmCreators;

    @Schema(description = "Сеансы фильма")
    private TreeSet<FilmSessionDTO> filmSessions;

    @Schema(description = "Отзывы о фильме")
    private TreeSet<ReviewDTO> reviews;
}
