package ru.sbercourse.cinema.ticketoffice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "film_creators")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SequenceGenerator(name = "default_gen", sequenceName = "film_creators_seq", allocationSize = 1, initialValue = 14)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "jsonId")
public class FilmCreator extends GenericModel{

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "position", nullable = false)
    private String position;

}
