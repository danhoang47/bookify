/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dao;
import Context.DBContext;
import java.util.List;
import java.util.ArrayList;
import app.dto.HotelAmenityDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class HotelAmenityDAO {
     public List<HotelAmenityDTO> get(String hotelId) throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "proc_getHotelAmenities @hotelId = ?";
        List<HotelAmenityDTO> hotelAmenityDtos = null;
        
        try {
            conn = DBContext.getConnection();
            cs = conn.prepareCall(sql);
            cs.setString(1, hotelId);
            rs = cs.executeQuery();
            hotelAmenityDtos = new ArrayList<>();
            
            while(rs.next()) {
                String id = rs.getString("hotel_amenities_id");
                String amenityId = rs.getString("amenity_id");
                String amenityName = rs.getString("amenity_name");
                String amenityTypeId = rs.getString("amenity_type_id");
                String amenityTypeName = rs.getString("amenity_type_name");
                String icon = rs.getString("icon");
                
                HotelAmenityDTO hotelAmenityDto 
                        = new HotelAmenityDTO(id, amenityId, hotelId, icon,
                        amenityName, amenityTypeName,
                        amenityTypeId);
               
                hotelAmenityDtos.add(hotelAmenityDto);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            } 
            if (cs != null) {
                cs.close();
            }
        }
        
        return hotelAmenityDtos;
    }
    
    public static void main(String[] args) throws SQLException {
        HotelAmenityDAO dao = new HotelAmenityDAO();
        System.out.println(dao.get("fe1f3fd7-6b6f-4450-b8c5-9f1ccee123a9").size());
    }
}
