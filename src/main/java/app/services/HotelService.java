/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.services;

import app.dao.AmenityDAO;
import app.dao.HotelDAO;
import app.dto.AmenityDTO;
import app.dto.HotelDTO;
import app.repository.HotelRepository;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class HotelService {
    final private HotelRepository hotelRepo;

    public HotelService() {
        hotelRepo = new HotelRepository();
    }
    
    public HotelDTO get(String hotelId) throws SQLException, ClassNotFoundException {
        return hotelRepo.get(hotelId);
    }
    
    public boolean addNewHotel(HotelDTO hotel) {
        return hotelRepo.addNewHotel(hotel);
    }
    
    public List<HotelDTO> getAllHotelBasicInfo() throws SQLException, ClassNotFoundException {
        return hotelRepo.getAllHotel();
    }

    public List<HotelDTO> getFilterHotel(String type, String id) throws SQLException, ClassNotFoundException {
        return hotelRepo.getFilterHotels(type, id);
    }

    public List<HotelDTO> getFilterHotelAdvance(String houseType, List<String> amenitiesPicked, int rooms, int numberOfBed, int numberOfBathroom, int min, int max) throws SQLException {
        return hotelRepo.getFilterHotelsAdvance(houseType, amenitiesPicked, rooms, numberOfBed, numberOfBathroom, min, max);
    }
}
