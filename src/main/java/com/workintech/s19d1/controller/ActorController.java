package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.service.ActorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/actor")
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public ResponseEntity<List<Actor>> findAll() {
        try {
            return ResponseEntity.ok(actorService.findAll());
        } catch (Exception e) {
            log.error("Error fetching all actors: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(actorService.findById(id));
        } catch (Exception e) {
            log.error("Error fetching actor with id {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<Actor> save(@RequestBody ActorRequest actorRequest) {
        try {
            Actor actor = actorRequest.getActor();
            if (actorRequest.getMovies() != null) {
                actor.setMovies(actorRequest.getMovies());
            }
            return ResponseEntity.ok(actorService.save(actor));
        } catch (Exception e) {
            log.error("Error saving actor: {}", e.getMessage());
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actor> update(@PathVariable Long id, @RequestBody Actor updatedActor) {
        try {
            Actor existing = actorService.findById(id);
            existing.setFirstName(updatedActor.getFirstName());
            existing.setLastName(updatedActor.getLastName());
            existing.setGender(updatedActor.getGender());
            existing.setBirthDate(updatedActor.getBirthDate());
            return ResponseEntity.ok(actorService.save(existing));
        } catch (Exception e) {
            log.error("Error updating actor with id {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Actor> delete(@PathVariable Long id) {
        try {
            Actor actor = actorService.findById(id);
            actorService.delete(actor);
            return ResponseEntity.ok(actor);
        } catch (Exception e) {
            log.error("Error deleting actor with id {}: {}", id, e.getMessage());
            throw e;
        }
    }
}