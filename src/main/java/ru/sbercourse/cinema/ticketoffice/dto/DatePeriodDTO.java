package ru.sbercourse.cinema.ticketoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DatePeriodDTO {

    private LocalDate startDate;
    private LocalDate endDate;
}
