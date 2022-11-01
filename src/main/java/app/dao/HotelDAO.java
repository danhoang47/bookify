/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dao;
import app.dto.HotelDTO;
import Context.DBContext;
import java.sql.CallableStatement;
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
public class HotelDAO {
    
    public HotelDTO get(String id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from Hotel where hotel_id = ?";
        HotelDTO hotel = null;
        
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareCall(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                String ownerId = rs.getString("user_id");
                String hotelTypeId = rs.getString("hoteltype_id");
                String name = rs.getString("hotel_name");
                String backgroundImage = rs.getString("background_image");
                String description = rs.getString("description");
                String country = rs.getString("country");
                String city = rs.getString("city");
                String district = rs.getString("district");
                String address = rs.getString("address");
                boolean isAllowPet = rs.getBoolean("isAllowPet");
                boolean isHasCamera = rs.getBoolean("isHasCamera");
                String checkin = rs.getString("checkin");
                String checkout = rs.getString("checkout");
                String closing = rs.getString("closing");
                String opening = rs.getString("opening");
                hotel = new HotelDTO(id, ownerId, 
                        hotelTypeId, name, backgroundImage, 
                        isAllowPet, isAllowPet, isHasCamera, 
                        description, country, district, city, 
                        address, closing, opening, checkin, checkout, null, null);
                
                System.out.println(hotel.getHotelName());
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
        
        return hotel;
    }
    
    public void update(HotelDTO hotel) throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        String sql = "proc_updateHotel @hotelId = ?, "
                + "@hotelTypeId = ?, @hotelName = ?, @backgroundImage = ?, "
                + "@description = ?, @country = ?, @district = ?, @city = ?, "
                + "@address = ?, @isAllowPet = ?, @isHasCamera = ?, "
                + "@closing = ?, @opening = ?, @checkin = ?, @checkout = ?" ;
        
        try {
            conn = DBContext.getConnection();
            cs = conn.prepareCall(sql);
            cs.setString(1, hotel.getHotelId());
            cs.setString(2, hotel.getHotelTypeId());
            cs.setString(3, hotel.getHotelName());
            cs.setString(4, hotel.getBackgroundImg());
            cs.setString(5, hotel.getDescription());
            cs.setString(6, hotel.getCountry());
            cs.setString(7, hotel.getDistrict());
            cs.setString(8, hotel.getCity());
            cs.setString(9, hotel.getAddress());
            cs.setBoolean(10, hotel.isIsAllowPet());
            cs.setBoolean(11, hotel.isIsHasCamera());
            cs.setString(12, hotel.getClosing());
            cs.setString(13, hotel.getOpening());
            cs.setString(14, hotel.getCheckin());
            cs.setString(15, hotel.getCheckout());
            cs.executeUpdate();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        HotelDAO dao = new HotelDAO();
        System.out.println(dao.get("fe1f3fd7-6b6f-4450-b8c5-9f1ccee123a9"));
    }
}
