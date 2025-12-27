package com.examly.springapp.repository;

import com.examly.springapp.model.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomCategoryRepo extends JpaRepository<RoomCategory, Long> {
    
}
