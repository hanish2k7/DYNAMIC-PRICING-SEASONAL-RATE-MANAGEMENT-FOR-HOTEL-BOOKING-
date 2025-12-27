package com.examly.springapp.service;

import com.examly.springapp.model.Booking;
import java.util.List;

public interface BookingService {
    Booking saveBooking(Booking booking);
    List<Booking> getAllBookings();
    Booking getBookingById(Long id);
    Booking updateBooking(Long id, Booking booking);
    void deleteBooking(Long id);
}
