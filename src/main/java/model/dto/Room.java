/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dto;

/**
 *
 * @author toten
 */
public class Room {
    private String room_id;
    private String hotel_id;
    private String room_num;
    private double roomPrice;
    private boolean avaiable;

    public Room(String room_id, String hotel_id, String room_num, double roomPrice, boolean avaiable) {
        this.room_id = room_id;
        this.hotel_id = hotel_id;
        this.room_num = room_num;
        this.roomPrice = roomPrice;
        this.avaiable = avaiable;
    }
    
    public Room(String room_num, double roomPrice, boolean avaiable) {
        this.room_num = room_num;
        this.roomPrice = roomPrice;
        this.avaiable = avaiable;
    }
    
    public Room() {
        
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getRoom_num() {
        return room_num;
    }

    public void setRoom_num(String room_num) {
        this.room_num = room_num;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public boolean isAvaiable() {
        return avaiable;
    }

    public void setAvaiable(boolean avaiable) {
        this.avaiable = avaiable;
    }

    @Override
    public String toString() {
        return "Room{" + "room_id=" + room_id + ", hotel_id=" + hotel_id + ", room_num=" + room_num + ", roomPrice=" + roomPrice + ", avaiable=" + avaiable + '}';
    }
    
    
}
