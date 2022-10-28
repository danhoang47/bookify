/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.services;

import app.dao.AmenityDAO;
import app.dto.AmenityDTO;
import app.dto.AmenityTypeDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class AmenityService {
    private static AmenityDAO dao;
    
    public AmenityService() {
        dao = new AmenityDAO();
    }
    
    public List<AmenityDTO> getDefaultAmenities() throws SQLException {
        
        return dao.getAll();
    }
    
    public List<AmenityTypeDTO> getDefaultAmenityTypes() throws SQLException {
        return dao.getTypes();
    }
}
