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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class HotelAmenityDAO {
    
     private Connection conn;
     private PreparedStatement ps;
     private CallableStatement cs;
     private ResultSet rs;
    
     public List<HotelAmenityDTO> get(String hotelId) throws SQLException {

        String sql = "proc_getHotelAmenities @hotelId = ?";
        List<HotelAmenityDTO> hotelAmenityDtos = null;
        
        try {
            conn = new DBContext().getConnection();
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
     
     



    public boolean addHotelAmenties(List<String> amenties, String hotel_id) {
        List<Integer> check = new ArrayList<>();

        try {
            String query = "insert into HotelAmenities values (?, ?,  ?)";
            conn = new DBContext().getConnection();

            for (int i = 0; i < amenties.size(); i++) {
                UUID uuid = UUID.randomUUID();
                ps = conn.prepareStatement(query);
                
                ps.setString(1, uuid.toString());
                ps.setString(2, amenties.get(i));
                ps.setString(3, hotel_id);
                int x = ps.executeUpdate();

                if (x != 0) {
                    check.add(x);
                }
            }

            if (check.size() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(dao.HotelAmenityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static void main(String[] args) throws SQLException {
        HotelAmenityDAO dao = new HotelAmenityDAO();
        System.out.println(dao.get("fe1f3fd7-6b6f-4450-b8c5-9f1ccee123a9").size());
    }
}
