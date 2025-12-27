package com.examly.springapp.controller;

import com.examly.springapp.model.Guest;
import com.examly.springapp.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @PostMapping
    public ResponseEntity<Object> addGuest(@RequestBody Map<String, Object> guestData) {
        Guest guest = new Guest();
        guest.setName((String) guestData.get("name"));
        guest.setPhone((String) guestData.get("phone"));
        guest.setEmail((String) guestData.get("email"));
        
        Guest saved = guestService.saveGuest(guest);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToMap(saved));
    }

    @GetMapping
    public ResponseEntity<Object> getAllGuests() {
        List<Guest> guests = guestService.getAllGuests();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Guest guest : guests) {
            result.add(convertToMap(guest));
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getGuestById(@PathVariable Long id) {
        Guest guest = guestService.getGuestById(id);
        if (guest == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToMap(guest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGuest(
            @PathVariable Long id,
            @RequestBody Map<String, Object> guestData) {
        
        Guest guest = new Guest();
        guest.setName((String) guestData.get("name"));
        guest.setPhone((String) guestData.get("phone"));
        guest.setEmail((String) guestData.get("email"));
        
        Guest updated = guestService.updateGuest(id, guest);
        return ResponseEntity.ok(convertToMap(updated));
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<Object> getGuestsByPhone(@PathVariable String phone) {
        List<Guest> guests = guestService.getGuestsByPhone(phone);
        if (guests.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No guest found with phone: " + phone);
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (Guest guest : guests) {
            result.add(convertToMap(guest));
        }
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<Object> getGuestsByEmail(@PathVariable String email) {
        List<Guest> guests = guestService.getGuestsByEmail(email);
        if (guests.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No guest found with email: " + email);
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (Guest guest : guests) {
            result.add(convertToMap(guest));
        }
        return ResponseEntity.ok(result);
    }
    
    private Map<String, Object> convertToMap(Guest guest) {
        Map<String, Object> map = new HashMap<>();
        map.put("guestId", guest.getGuestId());
        map.put("name", guest.getName());
        map.put("phone", guest.getPhone());
        map.put("email", guest.getEmail());
        return map;
    }
}
