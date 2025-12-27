package com.examly.springapp.controller;

import com.examly.springapp.model.Room;
import com.examly.springapp.model.RoomCategory;
import com.examly.springapp.service.RoomService;
import com.examly.springapp.repository.RoomCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;
    
    @Autowired
    private RoomCategoryRepo roomCategoryRepo;

    @PostMapping
    public ResponseEntity<Object> addRoom(@RequestBody Map<String, Object> roomData) {
        Room room = new Room();
        room.setRoomNumber((String) roomData.get("roomNumber"));
        room.setPricePerNight(((Number) roomData.get("pricePerNight")).doubleValue());
        room.setAvailable((Boolean) roomData.get("available"));
        
        // Handle room category
        Map<String, Object> categoryData = (Map<String, Object>) roomData.get("roomCategory");
        if (categoryData != null) {
            Long categoryId = ((Number) categoryData.get("categoryId")).longValue();
            Optional<RoomCategory> category = roomCategoryRepo.findById(categoryId);
            if (category.isPresent()) {
                room.setRoomCategory(category.get());
            }
        }
        
        Room saved = roomService.saveRoom(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToMap(saved));
    }

    @GetMapping
    public ResponseEntity<Object> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Room room : rooms) {
            result.add(convertToMap(room));
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRoomById(@PathVariable Long id) {
        Room room = roomService.getRoomById(id);
        if (room == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToMap(room));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRoom(
            @PathVariable Long id,
            @RequestBody Map<String, Object> roomData) {

        Room room = new Room();
        room.setRoomNumber((String) roomData.get("roomNumber"));
        room.setPricePerNight(((Number) roomData.get("pricePerNight")).doubleValue());
        room.setAvailable((Boolean) roomData.get("available"));
        
        // Handle room category
        Map<String, Object> categoryData = (Map<String, Object>) roomData.get("roomCategory");
        if (categoryData != null) {
            Long categoryId = ((Number) categoryData.get("categoryId")).longValue();
            Optional<RoomCategory> category = roomCategoryRepo.findById(categoryId);
            if (category.isPresent()) {
                room.setRoomCategory(category.get());
            }
        }
        
        Room updated = roomService.updateRoom(id, room);
        return ResponseEntity.ok(convertToMap(updated));
    }
    
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<Object> getRoomsByCategoryName(@PathVariable String categoryName) {
        List<Room> rooms = roomService.getRoomsByCategoryName(categoryName);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Room room : rooms) {
            result.add(convertToMap(room));
        }
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/number/{roomNumber}")
    public ResponseEntity<Object> getRoomsByNumber(@PathVariable String roomNumber) {
        List<Room> rooms = roomService.getRoomsByNumber(roomNumber);
        if (rooms.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No room found with number: " + roomNumber);
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (Room room : rooms) {
            result.add(convertToMap(room));
        }
        return ResponseEntity.ok(result);
    }
    
    private Map<String, Object> convertToMap(Room room) {
        Map<String, Object> map = new HashMap<>();
        map.put("roomId", room.getRoomId());
        map.put("roomNumber", room.getRoomNumber());
        map.put("pricePerNight", room.getPricePerNight());
        map.put("available", room.isAvailable());
        if (room.getRoomCategory() != null) {
            Map<String, Object> categoryMap = new HashMap<>();
            categoryMap.put("categoryId", room.getRoomCategory().getCategoryId());
            categoryMap.put("categoryName", room.getRoomCategory().getCategoryName());
            map.put("roomCategory", categoryMap);
        }
        return map;
    }
}
