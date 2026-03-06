package com.workintech.s19d1.controller;

import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/workintech/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> findAll() {
        try {
            return ResponseEntity.ok(movieService.findAll());
        } catch (Exception e) {
            log.error("Error fetching all movies: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(movieService.findById(id));
        } catch (Exception e) {
            log.error("Error fetching movie with id {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<Movie> save(@RequestBody Movie movie) {
        try {
            return ResponseEntity.ok(movieService.save(movie));
        } catch (Exception e) {
            log.error("Error saving movie: {}", e.getMessage());
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> update(@PathVariable Long id, @RequestBody Movie movie) {
        try {
            Movie existing = movieService.findById(id);
            existing.setName(movie.getName());
            existing.setDirectorName(movie.getDirectorName());
            existing.setRating(movie.getRating());
            existing.setReleaseDate(movie.getReleaseDate());
            return ResponseEntity.ok(movieService.save(existing));
        } catch (Exception e) {
            log.error("Error updating movie with id {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> delete(@PathVariable Long id) {
        try {
            Movie movie = movieService.findById(id);
            return ResponseEntity.ok(movieService.delete(movie));
        } catch (Exception e) {
            log.error("Error deleting movie with id {}: {}", id, e.getMessage());
            throw e;
        }
    }
}