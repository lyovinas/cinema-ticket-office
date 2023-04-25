package ru.sbercourse.cinema.ticketoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO заказа")
public class OrderDTO extends GenericDTO {

    @Schema(description = "Идентификатор пользователя сервиса")
    private Long userId;

    @Schema(description = "Идентификатор киносеанса")
    private Long filmSessionId;

    @Schema(description = "Идентификаторы мест в кинозале")
    private Set<Long> seatIds;

    @Schema(description = "Стоимость")
    private double cost;

    @Schema(description = "Куплено/забронировано")
    private boolean purchase;
}
