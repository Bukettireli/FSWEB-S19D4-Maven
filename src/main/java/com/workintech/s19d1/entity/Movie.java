package com.workintech.s19d1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
@Data
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Movie name cannot be blank")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Director name cannot be blank")
    @Column(name = "director_name", nullable = false)
    private String directorName;

    @Column(name = "rating")
    private int rating;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @JsonIgnore
    @ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
    private List<Actor> actors = new ArrayList<>();

    public void addActor(Actor actor) {
        this.actors.add(actor);
        actor.getMovies().add(this);
    }
}