package ru.sbercourse.cinema.ticketoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "DTO заказа")
public class OrderDto extends GenericDto {

    @Schema(description = "Пользователь сервиса")
    private Long userId;

    @Schema(description = "Киносеанс")
    private Long filmSessionId;

    @Schema(description = "Место в кинозале")
    private Long placementId;

    @Schema(description = "Стоимость")
    private double cost;

    @Schema(description = "Куплено/забронировано")
    private boolean purchase;
}
