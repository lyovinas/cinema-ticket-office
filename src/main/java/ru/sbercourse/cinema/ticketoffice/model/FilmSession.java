package ru.sbercourse.cinema.ticketoffice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "film_sessions")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SequenceGenerator(name = "default_gen", sequenceName = "film_sessions_seq", allocationSize = 1)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "jsonId")
public class FilmSession extends GenericModel{

    @ManyToOne
    @JoinColumn(name = "film_id", foreignKey = @ForeignKey(name = "fk_filmsessions_films"), nullable = false)
    private Film film;

    @Column(name = "start", nullable = false)
    private LocalDateTime start;

    @Column(name = "price", nullable = false)
    private double price;

    @OneToMany(mappedBy = "filmSession")
    private Set<Order> orders;

    @OneToMany(mappedBy = "filmSession")
    private Set<Booking> bookings;
}
