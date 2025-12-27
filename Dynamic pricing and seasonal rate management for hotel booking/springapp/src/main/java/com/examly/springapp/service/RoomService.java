package com.examly.springapp.service;

import com.examly.springapp.model.Room;
import java.util.List;

public interface RoomService {
    Room saveRoom(Room room);
    List<Room> getAllRooms();
    Room getRoomById(Long id);
    Room updateRoom(Long id, Room room);
    void deleteRoom(Long id);
    List<Room> getRoomsByCategoryName(String categoryName);
    List<Room> getRoomsByNumber(String roomNumber);
}
