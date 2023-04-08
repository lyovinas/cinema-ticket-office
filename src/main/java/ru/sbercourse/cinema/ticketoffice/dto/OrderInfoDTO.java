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

//    @Column(name = "id", table = "orders")
    private Long orderId;

//    @Column(name = "created_when", table = "orders")
    protected LocalDateTime orderCreatedWhen;

//    @Column(name = "title", table = "films")
    private String filmTitle;

//    @Column(name = "cost", table = "orders")
    private double cost;

//    @Column(name = "purchase", table = "orders")
    private boolean purchase;
}
