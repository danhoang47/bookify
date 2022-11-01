/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.services;

import app.repository.RoomRepository;

/**
 *
 * @author toten
 */
public class RoomService {
    private RoomRepository roomRepo;
    
    public RoomService() {
        roomRepo = new RoomRepository();
    }
    
    public boolean addNewRoom(String hotel_id, int numberOfRoom, int roomPrice, String bedType, int bedNumber, String bathType, int bathNumber, boolean isPrivateBath, 
            String guests_id, int numberOfGuest, int numberOfBedRoom) {
        return roomRepo.addRoom(hotel_id, numberOfRoom, roomPrice, bedType, bedNumber, bathType, bathNumber, isPrivateBath, guests_id, numberOfGuest, numberOfBedRoom);
    }
}
