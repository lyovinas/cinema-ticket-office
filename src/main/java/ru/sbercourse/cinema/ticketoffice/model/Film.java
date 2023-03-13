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
@Table(name = "films")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SequenceGenerator(name = "default_gen", sequenceName = "films_seq", allocationSize = 1)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "jsonId")
public class Film extends GenericModel{

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "release_year", nullable = false, length = 4)
    private Short releaseYear;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "genre", nullable = false)
    @Enumerated
    private Genre genre;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "films_creators",
            joinColumns = @JoinColumn(name = "film_id"), foreignKey = @ForeignKey(name = "fk_films_creators"),
            inverseJoinColumns = @JoinColumn(name = "creator_id"), inverseForeignKey = @ForeignKey(name = "fk_creators_films"))
    private Set<Creator> creators;

    @ToString.Exclude
    @OneToMany(mappedBy = "film")
    private Set<FilmSession> filmSessions;
}
