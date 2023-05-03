package ru.sbercourse.cinema.ticketoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDTO {

    private Long orderId;

    protected LocalDateTime orderCreatedWhen;

    private String filmTitle;

    private double cost;

    private boolean purchase;
}
