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
@Schema(description = "DTO места в кинозале")
public class SeatDTO extends GenericDTO {

    @Schema(description = "Ряд")
    private Byte row;

    @Schema(description = "Место в ряду")
    private Byte place;

}
