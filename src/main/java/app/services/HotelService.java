/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.services;

import app.dto.HotelDTO;
import app.repository.HotelRepository;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class HotelService {
    private HotelRepository hotelRepo;

    public HotelService() {
        hotelRepo = new HotelRepository();
    }
    
    public HotelDTO get(String hotelId) throws SQLException, ClassNotFoundException {
        return hotelRepo.get(hotelId);
    }
}
