package com.examly.springapp.service;

import com.examly.springapp.model.RoomCategory;
import com.examly.springapp.repository.RoomCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RoomCategoryServiceImpl implements RoomCategoryService {

    @Autowired
    private RoomCategoryRepo roomCategoryRepo;

    @Override
    public Map<String, String> saveCategory(Map<String, String> category) {
        RoomCategory roomCategory = new RoomCategory();
        roomCategory.setCategoryName(category.get("categoryName"));
        RoomCategory saved = roomCategoryRepo.save(roomCategory);
        
        Map<String, String> result = new HashMap<>();
        result.put("categoryName", saved.getCategoryName());
        return result;
    }

    @Override
    public List<Map<String, String>> getAllCategories() {
        List<RoomCategory> categories = roomCategoryRepo.findAll();
        List<Map<String, String>> result = new ArrayList<>();
        
        for (RoomCategory category : categories) {
            Map<String, String> categoryMap = new HashMap<>();
            categoryMap.put("categoryName", category.getCategoryName());
            result.add(categoryMap);
        }
        
        return result;
    }

    @Override
    public Map<String, String> getCategoryById(Long id) {
        Optional<RoomCategory> category = roomCategoryRepo.findById(id);
        if (category.isPresent()) {
            Map<String, String> result = new HashMap<>();
            result.put("categoryName", category.get().getCategoryName());
            return result;
        }
        return null;
    }

    @Override
    public Map<String, String> updateCategory(Long id, Map<String, String> category) {
        Optional<RoomCategory> existing = roomCategoryRepo.findById(id);
        if (existing.isPresent()) {
            RoomCategory roomCategory = existing.get();
            roomCategory.setCategoryName(category.get("categoryName"));
            RoomCategory updated = roomCategoryRepo.save(roomCategory);
            
            Map<String, String> result = new HashMap<>();
            result.put("categoryName", updated.getCategoryName());
            return result;
        }
        return null;
    }
}
