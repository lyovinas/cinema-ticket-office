package ru.sbercourse.cinema.ticketoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "DTO категории места в кинозале")
public class PlaceClassDto extends GenericDto {

    @Schema(description = "Название категории", example = "Стандарт")
    private String title;

    @Schema(description = "Описание категории", example = "Стандартное место")
    private String description;

    @Schema(description = "Коэффициент изменения стоимости")
    private double ratio;

//    @Schema(description = "Идентификаторы мест в кинозале")
//    private Set<Long> placementsIds;
}
