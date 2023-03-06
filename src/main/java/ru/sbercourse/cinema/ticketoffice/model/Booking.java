package ru.sbercourse.cinema.ticketoffice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "bookings")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SequenceGenerator(name = "default_gen", sequenceName = "bookings_seq", allocationSize = 1)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "jsonId")
public class Booking extends GenericModel{

    @ManyToOne(optional = false)
    @JoinColumn(name = "film_session_id", foreignKey = @ForeignKey(name = "fk_bookings_filmsessions"), nullable = false)
    private FilmSession filmSession;

    @ManyToOne(optional = false)
    @JoinColumn(name = "placement_id", foreignKey = @ForeignKey(name = "fk_bookings_placements"), nullable = false)
    private Placement placement;
}
