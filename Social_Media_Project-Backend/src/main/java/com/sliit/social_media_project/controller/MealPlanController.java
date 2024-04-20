package com.sliit.social_media_project.controller;

import com.sliit.social_media_project.models.MealPlan;
import com.sliit.social_media_project.service.MealPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MealPlanController {

    @Autowired
    private MealPlanService mealPlanService;

    @PostMapping("/meal_plans/user/{userId}")
    public ResponseEntity<?> createMealPlan(@RequestBody MealPlan mealPlan, @PathVariable Integer userId) {
        try {
            MealPlan newMealPlan = mealPlanService.saveMealPlan(mealPlan, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(newMealPlan);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/meal_plans/{id}")
    public ResponseEntity<?> getMealPlanById(@PathVariable Long id) {
        MealPlan mealPlan = mealPlanService.getMealPlanById(id);
        if (mealPlan != null) {
            // If mealPlan is found, return it with OK status
            return ResponseEntity.ok(mealPlan);
        } else {
            // If mealPlan is not found, return not found status
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/meal_plans/all")
    public ResponseEntity<List<MealPlan>> getAllMealPlans() {
        List<MealPlan> mealPlans = mealPlanService.getAllMealPlans();
        return ResponseEntity.ok(mealPlans);
    }

    @PutMapping("/meal_plans/{id}/user/{userId}")
    public ResponseEntity<?> updateMealPlan(@PathVariable Long id, @RequestBody MealPlan updatedMealPlan, @PathVariable Integer userId) {
        try {
            MealPlan mealPlan = mealPlanService.updateMealPlan(id, updatedMealPlan, userId);
            return ResponseEntity.ok(mealPlan);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @DeleteMapping("/meal_plans/{id}/user/{userId}")
    public ResponseEntity<?> deleteMealPlan(@PathVariable Long id, @PathVariable Integer userId) {
        try {
            mealPlanService.deleteMealPlan(id, userId);
            return ResponseEntity.ok("Meal plan deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}


