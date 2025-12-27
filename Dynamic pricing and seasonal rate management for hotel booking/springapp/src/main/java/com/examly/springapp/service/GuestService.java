package com.examly.springapp.service;

import com.examly.springapp.model.Guest;
import java.util.List;

public interface GuestService {
    Guest saveGuest(Guest guest);
    List<Guest> getAllGuests();
    Guest getGuestById(Long id);
    Guest updateGuest(Long id, Guest guest);
    void deleteGuest(Long id);
    List<Guest> getGuestsByPhone(String phone);
    List<Guest> getGuestsByEmail(String email);
}
