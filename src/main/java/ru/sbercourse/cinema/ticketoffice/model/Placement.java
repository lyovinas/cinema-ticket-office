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
@Table(name = "placements")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SequenceGenerator(name = "default_gen", sequenceName = "placements_seq", allocationSize = 1)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "jsonId")
public class Placement extends GenericModel{

    @Column(name = "row", nullable = false)
    private int row;

    @Column(name = "place", nullable = false)
    private int place;

    @ManyToOne(optional = false)
    @JoinColumn(name = "place_class_id", foreignKey = @ForeignKey(name = "fk_placements_categories"), nullable = false)
    private PlaceClass placeClass;

//    @ToString.Exclude
//    @OneToMany(mappedBy = "placement")
//    private Set<Order> orders;
}
