package com.examly.springapp.model;
import jakarta.persistence.*;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    private String roomNumber;
    private double pricePerNight;
    private boolean available;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private RoomCategory roomCategory;
    
    public Room() {}
    
    public Room(String roomNumber, double pricePerNight, boolean available, RoomCategory roomCategory) {
        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
        this.available = available;
        this.roomCategory = roomCategory;
    }
    
    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }
    
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    
    public double getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(double pricePerNight) { this.pricePerNight = pricePerNight; }
    
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    
    public RoomCategory getRoomCategory() { return roomCategory; }
    public void setRoomCategory(RoomCategory roomCategory) { this.roomCategory = roomCategory; }
}
