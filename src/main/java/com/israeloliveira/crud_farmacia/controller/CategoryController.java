package com.israeloliveira.crud_farmacia.controller;

import com.israeloliveira.crud_farmacia.model.Category;
import com.israeloliveira.crud_farmacia.repositories.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(repository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<List<Category>> getByDescription(@PathVariable String description) {
        return ResponseEntity.ok(repository.findAllByDescriptionContainingIgnoreCase(description));
    }

    @PostMapping
    public ResponseEntity<Category> post(@RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(repository.save(category));
    }

    @PutMapping
    public ResponseEntity<Category> put(@Valid @RequestBody Category category) {
        return repository.findById(category.getId())
                .map(response -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(repository.save(category)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Category> category = repository.findById(id);

        if (category.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
        repository.deleteById(id);
    }
}