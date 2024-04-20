package com.sliit.social_media_project.service;

import com.sliit.social_media_project.models.MealPlan;
import com.sliit.social_media_project.models.User;
import com.sliit.social_media_project.repository.MealPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealPlanServiceImpl implements MealPlanService {

    @Autowired
    private MealPlanRepository mealPlanRepository;

    @Autowired
    private UserService userService;

    @Override
    public MealPlan saveMealPlan(MealPlan mealPlan, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        if (user == null) {
            throw new Exception("User not found with id: " + userId);
        }

        mealPlan.setUser(user); // Associate the meal plan with the user
        return mealPlanRepository.save(mealPlan);
    }

    @Override
    public MealPlan getMealPlanById(Long id) {
        return mealPlanRepository.findById(id).orElse(null);
    }

    @Override
    public List<MealPlan> getAllMealPlans() {
        return mealPlanRepository.findAll();
    }

    @Override
    public MealPlan updateMealPlan(Long id, MealPlan updatedMealPlan, Integer userId) throws Exception {
        // Retrieve the existing meal plan by its ID
        MealPlan mealPlan = mealPlanRepository.findById(id).orElse(null);
        if (mealPlan == null) {
            throw new Exception("Meal plan not found with id: " + id);
        }

        // Check if the user attempting to update the meal plan is the owner
        if (!mealPlan.getUser().getId().equals(userId)) {
            throw new Exception("You are not authorized to update this meal plan");
        }

        // Update the meal plan fields with the new data
        mealPlan.setMealPlanName(updatedMealPlan.getMealPlanName());
        mealPlan.setRecipes(updatedMealPlan.getRecipes());
        mealPlan.setNutritionalInfo(updatedMealPlan.getNutritionalInfo());
        mealPlan.setPortionSize(updatedMealPlan.getPortionSize());
        mealPlan.setDietaryPreference(updatedMealPlan.getDietaryPreference());

        // Save the updated meal plan
        return mealPlanRepository.save(mealPlan);
    }


    @Override
    public void deleteMealPlan(Long id, Integer userId) throws Exception {
        // Retrieve the meal plan by its ID
        MealPlan mealPlan = mealPlanRepository.findById(id).orElse(null);
        if (mealPlan == null) {
            throw new Exception("Meal plan not found with id: " + id);
        }

        // Check if the user attempting to delete the meal plan is the owner
        if (!mealPlan.getUser().getId().equals(userId)) {
            throw new Exception("You are not authorized to delete this meal plan");
        }

        // Proceed with the deletion
        mealPlanRepository.delete(mealPlan);
    }
}
