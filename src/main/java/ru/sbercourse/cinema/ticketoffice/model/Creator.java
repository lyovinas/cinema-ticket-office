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
@Table(name = "creators")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SequenceGenerator(name = "default_gen", sequenceName = "creators_seq", allocationSize = 1)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "jsonId")
public class Creator extends GenericModel{

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "position", nullable = false)
    private String position;

//    @ToString.Exclude
//    @ManyToMany(mappedBy = "creators")
//    private Set<Film> films;
}
