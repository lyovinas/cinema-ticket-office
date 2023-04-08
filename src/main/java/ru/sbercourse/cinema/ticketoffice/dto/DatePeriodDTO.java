package ru.sbercourse.cinema.ticketoffice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DatePeriodDTO {

    private LocalDate startDate;
    private LocalDate endDate;
}
