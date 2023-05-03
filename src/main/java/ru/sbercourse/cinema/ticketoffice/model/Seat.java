package ru.sbercourse.cinema.ticketoffice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "seats")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SequenceGenerator(name = "default_gen", sequenceName = "seats_seq", allocationSize = 1, initialValue = 26)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "jsonId")
public class Seat extends GenericModel{

    @Column(name = "row", nullable = false)
    private Byte row;

    @Column(name = "place", nullable = false)
    private Byte place;
}
