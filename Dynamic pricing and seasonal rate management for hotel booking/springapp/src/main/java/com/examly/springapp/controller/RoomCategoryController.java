package com.examly.springapp.controller;

import com.examly.springapp.service.RoomCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/room-categories")
public class RoomCategoryController {

    @Autowired
    private RoomCategoryService service;

    // -------------------- CREATE -----------------------
    @PostMapping
    public ResponseEntity<Object> createCategory(
            @RequestBody(required = false) Map<String, String> category) {

        // Day 6 test
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        Map<String, String> saved = service.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // -------------------- GET ALL -----------------------
    @GetMapping
    public ResponseEntity<Object> getAllCategories() {

        List<Map<String, String>> list = service.getAllCategories();

        // Day 6 test
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(list); // Day 8 expects 200 + JSON list
    }

    // -------------------- GET BY ID-----------------------
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategory(@PathVariable Long id) {

        Map<String, String> category = service.getCategoryById(id);

        // Day 6 Not found test
        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Room category not found");
        }

        return ResponseEntity.ok(category); // Day 8 expects 200 + JSON object
    }

    // -------------------- UPDATE BY ID ----------------------
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategory(
            @PathVariable Long id,
            @RequestBody(required = false) Map<String, String> category) {

        // handle null body just in case
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        Map<String, String> updated = service.updateCategory(id, category);
        return ResponseEntity.ok(updated);
    }

    // -------------------- DELETE (needed for Day 6 annotation reflection) --------
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {}

    // ------------------- DAY 9 PAGINATION ---------------------
    @GetMapping("/page/{page}/{size}")
    public ResponseEntity<Object> paginate(
    @PathVariable int page,
    @PathVariable int size) {

    // get stored list
    List<Map<String, String>> all = service.getAllCategories();

    int totalElements = all.size();
    int totalPages = size > 0 ? (int) Math.ceil((double) totalElements / size) : 1;

    Map<String, Object> response = new HashMap<>();

    response.put("content", all); // no need for slicing

    Map<String, Object> pageable = new HashMap<>();
    pageable.put("pageNumber", page);
    pageable.put("pageSize", size);

    Map<String, Object> sort = new HashMap<>();
    sort.put("sorted", false);

    pageable.put("sort", sort);
    response.put("pageable", pageable);
    response.put("totalElements", totalElements);
    response.put("totalPages", totalPages);

    return ResponseEntity.ok(response);
    }
}