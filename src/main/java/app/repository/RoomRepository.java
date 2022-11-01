/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.repository;

import app.dao.RoomTypeDAO;
import app.dao.RoomDAO;

/**
 *
 * @author toten
 */
public class RoomRepository {
     private RoomDAO roomDao;
    private RoomTypeDAO roomTypeDao;
    
    public RoomRepository() {
        roomDao = new RoomDAO();
        roomTypeDao = new RoomTypeDAO();
    }
    
    public boolean addRoom(String hotel_id, int numberOfRoom, int roomPrice, String bedType, int bedNumber, String bathType, int bathNumber, boolean isPrivateBath, 
            String guests_id, int numberOfGuest, int numberOfBedRoom) {
        String roomTypeId = roomTypeDao.addNewRoomType(roomPrice, bedType, bedNumber, bathType, bathNumber, isPrivateBath, guests_id, numberOfGuest, numberOfBedRoom);
        boolean addRoom = roomDao.addNewRoom(hotel_id, numberOfRoom, roomTypeId);
        
        return addRoom;
    }
}
