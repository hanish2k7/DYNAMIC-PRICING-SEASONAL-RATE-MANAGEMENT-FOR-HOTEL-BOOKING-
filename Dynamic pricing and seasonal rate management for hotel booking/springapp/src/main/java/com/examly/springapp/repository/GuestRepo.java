package com.examly.springapp.repository;

import com.examly.springapp.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GuestRepo extends JpaRepository<Guest, Long> {
    List<Guest> findByPhone(String phone);
    List<Guest> findByEmail(String email);
}
