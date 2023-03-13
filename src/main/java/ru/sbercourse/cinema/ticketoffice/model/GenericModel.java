package ru.sbercourse.cinema.ticketoffice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@ToString
public abstract class GenericModel {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    protected Long id;

    @Column(name = "created_when")
    protected LocalDateTime createdWhen;

    @Column(name = "created_by")
    protected String createdBy;
}
