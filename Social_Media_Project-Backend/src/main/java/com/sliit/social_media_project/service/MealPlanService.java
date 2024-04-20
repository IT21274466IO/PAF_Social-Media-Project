package com.sliit.social_media_project.service;

import com.sliit.social_media_project.models.MealPlan;

import java.util.List;

public interface MealPlanService {
    MealPlan saveMealPlan(MealPlan mealPlan, Integer userId) throws Exception;
    MealPlan getMealPlanById(Long id);
    List<MealPlan> getAllMealPlans();

    MealPlan updateMealPlan(Long id, MealPlan updatedMealPlan, Integer userId) throws Exception;

    void deleteMealPlan(Long id, Integer userId) throws Exception;
}