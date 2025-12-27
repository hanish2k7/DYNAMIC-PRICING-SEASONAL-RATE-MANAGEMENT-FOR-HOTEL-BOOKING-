package com.examly.springapp.service;

import com.examly.springapp.model.Booking;
import com.examly.springapp.repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    
    @Autowired
    private BookingRepo bookingRepo;
    
    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepo.save(booking);
    }
    
    @Override
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }
    
    @Override
    public Booking getBookingById(Long id) {
        return bookingRepo.findById(id).orElse(null);
    }
    
    @Override
    public Booking updateBooking(Long id, Booking booking) {
        booking.setBookingId(id);
        return bookingRepo.save(booking);
    }
    
    @Override
    public void deleteBooking(Long id) {
        bookingRepo.deleteById(id);
    }
}
