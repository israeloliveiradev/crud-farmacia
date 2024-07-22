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

    @Autowired
    private CategoryRepository categoryRepository;

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
    public ResponseEntity<Product> post(@Valid @RequestBody Product product) {
        if (categoryRepository.existsById(product.getCategory().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(repository.save(product));

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found!", null);

    }

    @PutMapping
    public ResponseEntity<Product> put(@Valid @RequestBody Product product) {
        if (repository.existsById(product.getId())) {

            if (categoryRepository.existsById(product.getCategory().getId()))
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(repository.save(product));

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found!", null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

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



