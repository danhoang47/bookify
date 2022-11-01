/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dto;


/**
 *
 * @author toten
 */
public class RoomTypeDTO {
    private String id;
    private int roomPrice;
    private String bedType;
    private String bedNumber;
    private String bathRoomType;
    private String bathRoomNumber;
    private String isBathRoomPrivate;
    private String guest_id;
    private int max_guest;
    private int number_of_room;

    public RoomTypeDTO(String id, int roomPrice, String bedType, String bedNumber, String bathRoomType, String bathRoomNumber, String isBathRoomPrivate, String guest_id, int max_guest, int number_of_room) {
        this.id = id;
        this.roomPrice = roomPrice;
        this.bedType = bedType;
        this.bedNumber = bedNumber;
        this.bathRoomType = bathRoomType;
        this.bathRoomNumber = bathRoomNumber;
        this.isBathRoomPrivate = isBathRoomPrivate;
        this.guest_id = guest_id;
        this.max_guest = max_guest;
        this.number_of_room = number_of_room;
    }

    public int getNumber_of_room() {
        return number_of_room;
    }

    public void setNumber_of_room(int number_of_room) {
        this.number_of_room = number_of_room;
    }

    

    public RoomTypeDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
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

    public String getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(String guest_id) {
        this.guest_id = guest_id;
    }

    public int getMax_guest() {
        return max_guest;
    }

    public void setMax_guest(int max_guest) {
        this.max_guest = max_guest;
    }

    @Override
    public String toString() {
        return "RoomTypeDTO{" + "id=" + id + ", roomPrice=" + roomPrice + ", bedType=" + bedType + ", bedNumber=" + bedNumber + ", bathRoomType=" + bathRoomType + ", bathRoomNumber=" + bathRoomNumber + ", isBathRoomPrivate=" + isBathRoomPrivate + ", guest_id=" + guest_id + ", max_guest=" + max_guest + ", number_of_room=" + number_of_room + '}';
    }

    
  
    
   
}
