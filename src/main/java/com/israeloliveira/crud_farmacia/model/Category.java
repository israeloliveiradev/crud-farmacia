package com.israeloliveira.crud_farmacia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "tb_category" )
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo nome é Obrigatório!")
    private String name;

    @NotBlank(message = "O atributo descrição é Obrigatório!")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O atributo nome é Obrigatório!") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "O atributo nome é Obrigatório!") String name) {
        this.name = name;
    }

    public @NotBlank(message = "O atributo descrição é Obrigatório!") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "O atributo descrição é Obrigatório!") String description) {
        this.description = description;
    }

    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "category", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("category")
    private List<Product> product;

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}

