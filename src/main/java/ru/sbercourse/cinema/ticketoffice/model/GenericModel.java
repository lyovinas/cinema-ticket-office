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

    @Column(name = "created_when", nullable = false)
    protected LocalDateTime createdWhen;

    @Column(name = "created_by", nullable = false)
    protected String createdBy;

    @Column(name = "is_deleted")
    protected boolean isDeleted = false;

    @Column(name = "deleted_when")
    protected LocalDateTime deletedWhen;

    @Column(name = "deleted_by")
    protected String deletedBy;

    @Column(name = "updated_when")
    protected LocalDateTime updatedWhen;

    @Column(name = "updated_by")
    protected String updatedBy;
}
