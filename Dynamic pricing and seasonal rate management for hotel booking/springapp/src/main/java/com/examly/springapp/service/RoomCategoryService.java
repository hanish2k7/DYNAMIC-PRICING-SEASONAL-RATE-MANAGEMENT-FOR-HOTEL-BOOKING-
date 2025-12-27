package com.examly.springapp.service;

import java.util.List;
import java.util.Map;

public interface RoomCategoryService {
    Map<String, String> saveCategory(Map<String, String> category);
    List<Map<String, String>> getAllCategories();
    Map<String, String> getCategoryById(Long id);
    Map<String, String> updateCategory(Long id, Map<String, String> category);
}
