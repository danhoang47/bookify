/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dto;

/**
 *
 * @author toten
 */
public class RoomDTO {
    private String room_id;
    private String hotel_id;
    private String room_type_id;

    public RoomDTO(String room_id, String hotel_id, String room_type_id) {
        this.room_id = room_id;
        this.hotel_id = hotel_id;
        this.room_type_id = room_type_id;
    }

    public RoomDTO() {
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

    public String getRoom_type_id() {
        return room_type_id;
    }

    public void setRoom_type_id(String room_type_id) {
        this.room_type_id = room_type_id;
    }

    @Override
    public String toString() {
        return "RoomDTO{" + "room_id=" + room_id + ", hotel_id=" + hotel_id + ", room_type_id=" + room_type_id + '}';
    }

    
   
    
}
