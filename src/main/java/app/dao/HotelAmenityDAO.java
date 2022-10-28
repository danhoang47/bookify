/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dao;
import Context.DBContext;
import java.util.List;
import java.util.ArrayList;
import app.dto.HotelAmenityDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from Image where hotel_id = ?";
        List<HotelAmenityDTO> hotelAmenityDtos = null;
        
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareCall(sql);
            ps.setString(1, hotelId);
            rs = ps.executeQuery();
            hotelAmenityDtos = new ArrayList<>();
            
            while(rs.next()) {
                String id = rs.getString("hotel_amenities_id");
                String amenityId = rs.getString("amenity_id");
                
                HotelAmenityDTO hotelAmenityDto = new HotelAmenityDTO(id, hotelId, src, type);
               
            }
            
        } catch (Exception ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            } 
            if (ps != null) {
                ps.close();
            }
        }
        
        return imageDtos;
    }
    
    public static void main(String[] args) throws SQLException {
        
        System.out.println(dao.get("fe1f3fd7-6b6f-4450-b8c5-9f1ccee123a9").size());
    }
}
