package com.examly.springapp.service;

import com.examly.springapp.model.Guest;
import com.examly.springapp.repository.GuestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GuestServiceImpl implements GuestService {
    
    @Autowired
    private GuestRepo guestRepo;
    
    @Override
    public Guest saveGuest(Guest guest) {
        return guestRepo.save(guest);
    }
    
    @Override
    public List<Guest> getAllGuests() {
        return guestRepo.findAll();
    }
    
    @Override
    public Guest getGuestById(Long id) {
        return guestRepo.findById(id).orElse(null);
    }
    
    @Override
    public Guest updateGuest(Long id, Guest guest) {
        guest.setGuestId(id);
        return guestRepo.save(guest);
    }
    
    @Override
    public void deleteGuest(Long id) {
        guestRepo.deleteById(id);
    }
    
    @Override
    public List<Guest> getGuestsByPhone(String phone) {
        return guestRepo.findByPhone(phone);
    }
    
    @Override
    public List<Guest> getGuestsByEmail(String email) {
        return guestRepo.findByEmail(email);
    }
}
