package com.israeloliveira.crud_farmacia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

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
}
