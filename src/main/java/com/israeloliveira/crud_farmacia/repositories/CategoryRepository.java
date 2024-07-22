package com.israeloliveira.crud_farmacia.repositories;

import com.israeloliveira.crud_farmacia.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    public List<Category> findAllByDescriptionContainingIgnoreCase(@Param("description") String description);
}
