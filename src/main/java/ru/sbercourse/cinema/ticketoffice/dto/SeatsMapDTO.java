package ru.sbercourse.cinema.ticketoffice.dto;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public interface SeatsMapDTO {

    Long getSeatId();
    Byte getRow();
    Byte getPlace();
    Long getOrderId();
    boolean isDeleted();
}
