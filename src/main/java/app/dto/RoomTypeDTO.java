/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dto;

/**
 *
 * @author ADMIN
 */
public class RoomTypeDTO {
    private String id;
    private int price;
    private String bedType;
    private int numberOfBed;
    private String bathroomType;
    private int numberOfBathroom;
    private int numberOfRoom;
    private int numberOfGuests;
    private boolean isPrivateBathroom;
    private String hotelId;
    private int rooms;

    public RoomTypeDTO() {
    }

    public RoomTypeDTO(String id, int price, String bedType, int numberOfBed, String bathroomType, int numberOfRoom, int numberOfGuests, String hotelId) {
        this.id = id;
        this.price = price;
        this.bedType = bedType;
        this.numberOfBed = numberOfBed;
        this.bathroomType = bathroomType;
        this.numberOfRoom = numberOfRoom;
        this.numberOfGuests = numberOfGuests;
        this.hotelId = hotelId;
    }

    public RoomTypeDTO(String id, int price, String bedType, int numberOfBed, String bathroomType, int numberOfBathroom, int numberOfRoom, int numberOfGuests, boolean isPrivateBathroom, String hotelId, int rooms) {
        this.id = id;
        this.price = price;
        this.bedType = bedType;
        this.numberOfBed = numberOfBed;
        this.bathroomType = bathroomType;
        this.numberOfBathroom = numberOfBathroom;
        this.numberOfRoom = numberOfRoom;
        this.numberOfGuests = numberOfGuests;
        this.hotelId = hotelId;
        this.isPrivateBathroom = isPrivateBathroom;
        this.rooms = rooms;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public boolean isIsPrivateBathroom() {
        return isPrivateBathroom;
    }

    public void setIsPrivateBathroom(boolean isPrivateBathroom) {
        this.isPrivateBathroom = isPrivateBathroom;
    }

    public String getBathroomType() {
        return bathroomType;
    }

    public int getNumberOfBathroom() {
        return numberOfBathroom;
    }

    public void setBathroomType(String bathroomType) {
        this.bathroomType = bathroomType;
    }

    public void setNumberOfBathroom(int numberOfBathroom) {
        this.numberOfBathroom = numberOfBathroom;
    }
    
    public String getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getBedType() {
        return bedType;
    }

    public int getNumberOfBed() {
        return numberOfBed;
    }

    public int getNumberOfRoom() {
        return numberOfRoom;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public void setNumberOfBed(int numberOfBed) {
        this.numberOfBed = numberOfBed;
    }

    public void setNumberOfRoom(int numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString() {
        return "RoomTypeDTO{" + "id=" + id + ", price=" + price + ", bedType=" + bedType + ", numberOfBed=" + numberOfBed + ", bathroomType=" + bathroomType + ", numberOfBathroom=" + numberOfBathroom + ", numberOfRoom=" + numberOfRoom + ", numberOfGuests=" + numberOfGuests + ", isPrivateBathroom=" + isPrivateBathroom + ", hotelId=" + hotelId + '}';
    }
}
