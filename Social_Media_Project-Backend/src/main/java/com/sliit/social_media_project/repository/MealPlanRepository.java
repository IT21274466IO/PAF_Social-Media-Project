package com.sliit.social_media_project.repository;

import com.sliit.social_media_project.models.MealPlan;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {
    @EntityGraph(attributePaths = {"user"})
    Optional<MealPlan> findById(Long id);

    @EntityGraph(attributePaths = {"user"})
    List<MealPlan> findAll();
}
