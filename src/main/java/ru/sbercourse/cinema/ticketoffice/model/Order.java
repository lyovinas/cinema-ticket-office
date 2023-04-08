package ru.sbercourse.cinema.ticketoffice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SequenceGenerator(name = "default_gen", sequenceName = "orders_seq", allocationSize = 1)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "jsonId")
public class Order extends GenericModel{

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_orders_users"), nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "film_session_id", foreignKey = @ForeignKey(name = "fk_orders_filmsessions"), nullable = false)
    private FilmSession filmSession;

//    @ManyToOne(optional = false)
//    @JoinColumn(name = "seat_id", foreignKey = @ForeignKey(name = "fk_orders_seats"), nullable = false)
    @ManyToMany(fetch = FetchType.EAGER)//, cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    @JoinTable(name = "orders_seats",
            joinColumns = @JoinColumn(name = "order_id"), foreignKey = @ForeignKey(name = "fk_orders_seats"),
            inverseJoinColumns = @JoinColumn(name = "seat_id"), inverseForeignKey = @ForeignKey(name = "fk_seats_orders"))
    private Set<Seat> seats;

    @Column(name = "cost", nullable = false)
    private double cost;

    @Column(name = "purchase", nullable = false)
    private boolean purchase;
}
