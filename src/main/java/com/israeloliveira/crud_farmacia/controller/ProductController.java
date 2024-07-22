package com.israeloliveira.crud_farmacia.controller;

import com.israeloliveira.crud_farmacia.model.Category;
import com.israeloliveira.crud_farmacia.model.Product;
import com.israeloliveira.crud_farmacia.repositories.CategoryRepository;
import com.israeloliveira.crud_farmacia.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(repository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/manufacturer/{manufacturer}")
    public ResponseEntity<List<Product>> getByManufacturer(@PathVariable String manufacturer) {
        return ResponseEntity.ok(repository.findAllByManufacturerContainingIgnoreCase(manufacturer));
    }

    @PostMapping
    public ResponseEntity<Product> post(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(repository.save(product));
    }

    @PutMapping
    public ResponseEntity<Product> put(@Valid @RequestBody Product product) {
        return repository.findById(product.getId())
                .map(response -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(repository.save(product)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Product> product = repository.findById(id);

        if (product.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
        repository.deleteById(id);
    }
}


