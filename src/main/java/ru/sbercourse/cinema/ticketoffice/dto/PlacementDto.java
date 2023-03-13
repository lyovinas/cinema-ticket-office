package ru.sbercourse.cinema.ticketoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "DTO места в кинозале")
public class PlacementDto extends GenericDto {

    @Schema(description = "Ряд")
    private int row;

    @Schema(description = "Место в ряду")
    private int place;

    @Schema(description = "Идентификатор категории места")
    private Long placeClassId;

//    @Schema(description = "Идентификаторы заказов", accessMode = Schema.AccessMode.READ_ONLY)
//    private Set<Long> ordersIds;
}
