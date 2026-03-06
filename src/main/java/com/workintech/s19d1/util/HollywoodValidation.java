package com.workintech.s19d1.util;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class HollywoodValidation {

    public void validateActor(Actor actor) {
        if (actor == null) {
            throw new ApiException("Actor cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (actor.getFirstName() == null || actor.getFirstName().isBlank()) {
            throw new ApiException("Actor first name cannot be blank", HttpStatus.BAD_REQUEST);
        }
        if (actor.getLastName() == null || actor.getLastName().isBlank()) {
            throw new ApiException("Actor last name cannot be blank", HttpStatus.BAD_REQUEST);
        }
        if (actor.getGender() == null) {
            throw new ApiException("Actor gender cannot be null", HttpStatus.BAD_REQUEST);
        }
    }

    public void validateMovie(Movie movie) {
        if (movie == null) {
            throw new ApiException("Movie cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (movie.getName() == null || movie.getName().isBlank()) {
            throw new ApiException("Movie name cannot be blank", HttpStatus.BAD_REQUEST);
        }
        if (movie.getDirectorName() == null || movie.getDirectorName().isBlank()) {
            throw new ApiException("Director name cannot be blank", HttpStatus.BAD_REQUEST);
        }
    }

    public void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new ApiException("Invalid id: " + id, HttpStatus.BAD_REQUEST);
        }
    }
}