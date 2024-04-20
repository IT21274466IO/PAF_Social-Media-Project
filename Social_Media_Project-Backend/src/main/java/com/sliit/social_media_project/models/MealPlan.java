package com.sliit.social_media_project.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Meal_Plans")
public class MealPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    private String mealPlanName;
    private String recipes;
    private String nutritionalInfo;
    private String portionSize;
    private String dietaryPreference;

    // Default constructor
    public MealPlan() {
    }

    // Parameterized constructor
    public MealPlan(String mealPlanName, String recipes, String nutritionalInfo, String portionSize, String dietaryPreference) {
        this.mealPlanName = mealPlanName;
        this.recipes = recipes;
        this.nutritionalInfo = nutritionalInfo;
        this.portionSize = portionSize;
        this.dietaryPreference = dietaryPreference;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMealPlanName() {
        return mealPlanName;
    }

    public void setMealPlanName(String mealPlanName) {
        this.mealPlanName = mealPlanName;
    }

    public String getRecipes() {
        return recipes;
    }

    public void setRecipes(String recipes) {
        this.recipes = recipes;
    }

    public String getNutritionalInfo() {
        return nutritionalInfo;
    }

    public void setNutritionalInfo(String nutritionalInfo) {
        this.nutritionalInfo = nutritionalInfo;
    }

    public String getPortionSize() {
        return portionSize;
    }

    public void setPortionSize(String portionSize) {
        this.portionSize = portionSize;
    }

    public String getDietaryPreference() {
        return dietaryPreference;
    }

    public void setDietaryPreference(String dietaryPreference) {
        this.dietaryPreference = dietaryPreference;
    }
}

