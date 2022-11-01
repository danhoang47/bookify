/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.services;

import app.dao.AmenityDAO;
import app.dao.HotelAmenityDAO;
import app.dto.AmenityDTO;
import app.dto.AmenityTypeDTO;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class AmenityService {
    private static AmenityDAO dao;
    private static HotelAmenityDAO had;
    
    public AmenityService() {
        dao = new AmenityDAO();
        had = new HotelAmenityDAO();
    }
    
    public List<AmenityDTO> getDefaultAmenities() throws SQLException {
        
        return dao.getAll();
    }
    
    public List<AmenityTypeDTO> getDefaultAmenityTypes() throws SQLException {
        return dao.getTypes();
    }
    
    public boolean addHotelAmenitiesAll(List<String> amenitiesId, List<String> amenitiesName, List<String> amenitiesTypes, String hotel_id) {
        List<String> currentAmenities = dao.listAllAmenityId();
        List<String> newAmenitiesId = Arrays.asList(amenitiesId.get(0).split("\\s*,\\s*"));
        
        List<String> newAmenitiesName = Arrays.asList(amenitiesName.get(0).split("\\s*,\\s*"));
        
        List<String> newAmenitiesTypes = Arrays.asList(amenitiesTypes.get(0).split("\\s*,\\s*"));
        
        System.out.println("List amenities iD new:  "+ newAmenitiesId);
        System.out.println("List amenities iD old:  "+ amenitiesId);

        
        for (int i = 0; i < newAmenitiesId.size(); i++) {
            if (!currentAmenities.contains(newAmenitiesId.get(i))) {
                dao.addAmenity(newAmenitiesId.get(i), newAmenitiesName.get(i), newAmenitiesTypes.get(i));
            }
        }
        
        boolean check = had.addHotelAmenties(newAmenitiesId, hotel_id);
        if (check) {
            return true;
        }
        return false;
    }
}
