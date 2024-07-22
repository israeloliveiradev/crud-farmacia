package com.israeloliveira.crud_farmacia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tb_product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank(message = "O atributo nome é Obrigatório!")
    private String name;

    @NotBlank(message = "O atributo descrição é Obrigatório!  Ex: Genérico, Referência, Similar")
    private String manufacturer;

    @Digits(integer = 6, fraction = 2)
    @NotNull
    private BigDecimal price;

    @ManyToOne
    @JsonIgnoreProperties("product")
    private Category category;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public @NotBlank(message = "O atributo nome é Obrigatório!") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "O atributo nome é Obrigatório!") String name) {
        this.name = name;
    }

    public @NotBlank(message = "O atributo descrição é Obrigatório!  Ex: Genérico, Referência, Similar") String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(@NotBlank(message = "O atributo descrição é Obrigatório!  Ex: Genérico, Referência, Similar") String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public @Digits(integer = 6, fraction = 2) @NotNull BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@Digits(integer = 6, fraction = 2) @NotNull BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

