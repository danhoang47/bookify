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
    private double roomPrice;
    private int type;
    private int max_guest;
    private String bedType;
    private String bedNumber;
    private String bathRoomType;
    private String bathRoomNumber;
    private String isBathRoomPrivate;

    public Room(String room_id, String hotel_id, double roomPrice, int type, int max_guest) {
        this.room_id = room_id;
        this.hotel_id = hotel_id;
        this.roomPrice = roomPrice;
        this.type = type;
        this.max_guest = max_guest;
    }

    public Room() {
    }

    public Room(String room_id, String hotel_id, double roomPrice, int type, int max_guest, String bedType, String bedNumber, String bathRoomType, String bathRoomNumber, String isBathRoomPrivate) {
        this.room_id = room_id;
        this.hotel_id = hotel_id;
        this.roomPrice = roomPrice;
        this.type = type;
        this.max_guest = max_guest;
        this.bedType = bedType;
        this.bedNumber = bedNumber;
        this.bathRoomType = bathRoomType;
        this.bathRoomNumber = bathRoomNumber;
        this.isBathRoomPrivate = isBathRoomPrivate;
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

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMax_guest() {
        return max_guest;
    }

    public void setMax_guest(int max_guest) {
        this.max_guest = max_guest;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getBathRoomType() {
        return bathRoomType;
    }

    public void setBathRoomType(String bathRoomType) {
        this.bathRoomType = bathRoomType;
    }

    public String getBathRoomNumber() {
        return bathRoomNumber;
    }

    public void setBathRoomNumber(String bathRoomNumber) {
        this.bathRoomNumber = bathRoomNumber;
    }

    public String getIsBathRoomPrivate() {
        return isBathRoomPrivate;
    }

    public void setIsBathRoomPrivate(String isBathRoomPrivate) {
        this.isBathRoomPrivate = isBathRoomPrivate;
    }

    @Override
    public String toString() {
        return "Room{" + "room_id=" + room_id + ", hotel_id=" + hotel_id + ", roomPrice=" + roomPrice + ", type=" + type + ", max_guest=" + max_guest + ", bedType=" + bedType + ", bedNumber=" + bedNumber + ", bathRoomType=" + bathRoomType + ", bathRoomNumber=" + bathRoomNumber + ", isBathRoomPrivate=" + isBathRoomPrivate + '}';
    }

   
    
    
    
}
