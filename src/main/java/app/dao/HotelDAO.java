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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class HotelDAO {
    
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public HotelDTO get(String id) throws SQLException, ClassNotFoundException {
        
        String sql = "select * from Hotel where hotel_id = ?";
        HotelDTO hotel = null;
        
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareCall(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            
            while (rs.next()) {
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

    public boolean addNewHotel(HotelDTO hotel) {
        try {
            String query = "INSERT INTO Hotel VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?, ?,  ? , ? , ? , ? )";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            
            ps.setString(1, hotel.getHotelId());
            ps.setString(2, hotel.getUserId());
            ps.setString(3, hotel.getHotelTypeId());
            ps.setString(4, hotel.getHotelName());
            ps.setString(5, hotel.getBackgroundImg());
            ps.setInt(6, 0);
            ps.setString(7, hotel.getDescription());
            ps.setString(8, hotel.getCountry());
            ps.setString(9, hotel.getDistrict());
            ps.setString(10, hotel.getCity());
            ps.setString(11, hotel.getAddress());
            ps.setInt(12, hotel.isIsHasCamera() == false ? 0 : 1);
            ps.setInt(13, hotel.isIsAllowPet() == false ? 0 : 1);
            ps.setString(14, hotel.getCheckin());
            ps.setString(15, hotel.getCheckout());
            ps.setString(16, hotel.getClosing());
            ps.setString(17, hotel.getOpening());
            
            int a = ps.executeUpdate();
            
            if (a == 1) {
                return true;
            } else {
                return false;
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(dao.HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List<HotelDTO> getAllHotelBasicInfo() throws SQLException {
        String query = "select * from getAllHotelBasicInfo";
        
        List<HotelDTO> listHotel = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                String hotelId = rs.getString("hotel_id");
                String hotelName = rs.getString("hotel_name");
                String hotelTypeId = rs.getString("hotelType_id");
                String bgImage = rs.getString("background_image");
                String country = rs.getString("country");
                String city = rs.getString("city");
                String district = rs.getString("district");
                String address = rs.getString("address");
                int averagePrice = rs.getInt("average_price");
                int rating = rs.getInt("rating");
                HotelDTO hotel = new HotelDTO(hotelId, hotelName, hotelTypeId, bgImage, country, city, district, address, averagePrice, rating);
                
                listHotel.add(hotel);
            }
            
            return listHotel;
            
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
        
        return null;
    }
    
    public List<HotelDTO> getFilterHotel(String type, String id) throws SQLException {
        String query = "";
        
        if (type.equals("hotel")) {
            query = "select * from getAllHotelBasicInfo where hoteltype_id=?";
        } else if (type.equals("amenity")) {
            query = "select * from getAllHotelBasicInfo where hotel_id in (select hotel_id from HotelAmenities where amenity_id=?)";
        }
        
        System.out.println(query);
        
        List<HotelDTO> listHotel = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            
            ps.setString(1, id);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                String hotelId = rs.getString("hotel_id");
                String hotelName = rs.getString("hotel_name");
                String hotelTypeId = rs.getString("hotelType_id");
                String bgImage = rs.getString("background_image");
                String country = rs.getString("country");
                String city = rs.getString("city");
                String district = rs.getString("district");
                String address = rs.getString("address");
                int averagePrice = rs.getInt("average_price");
                int rating = rs.getInt("rating");
                HotelDTO hotel = new HotelDTO(hotelId, hotelName, hotelTypeId, bgImage, country, city, district, address, averagePrice, rating);
                
                listHotel.add(hotel);
            }
            
            return listHotel;
            
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
        
        return null;
    }
    
    public List<HotelDTO> getFilterAdvancedHotel(String houseType, List<String> amenitiesPicked, int rooms, int numberOfBed, int numberOfBathroom, int min, int max) throws SQLException {
        
        String query = "select * from AdvancedFilter where number_of_room>=? and bed_number>=? and bath_number>=? and average_price between ? and ?";
        
        
        if (houseType.length()>0) {
            query += " and hoteltype_id=?";
        }
        if (amenitiesPicked.size() > 0 && !amenitiesPicked.get(0).equals("")) {
            System.out.println("size: " + amenitiesPicked.size());
            String listAmenities = "?";
            for (int i = 1; i < amenitiesPicked.size(); i++) {
                listAmenities += ",?";
            }
            query += " and hotel_id in (select hotel_id from HotelAmenities where amenity_id in (" + listAmenities + ") group by hotel_id having COUNT(*)="+ amenitiesPicked.size() +")";
        }
        
        List<HotelDTO> listHotel = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            
            ps.setInt(1, rooms);
            ps.setInt(2, numberOfBed);
            ps.setInt(3, numberOfBathroom);
            ps.setInt(4, min);
            ps.setInt(5, max==0 ? 100000 : max);
            int nextIndex = 5;
            if (houseType.length()>0) {
                nextIndex++;
                ps.setString(nextIndex, houseType);
            }
            
            if (amenitiesPicked.size() > 0 && !amenitiesPicked.get(0).equals("")) {
                nextIndex++;
                for (int i = 0; i < amenitiesPicked.size(); i++) {
                    ps.setString(nextIndex + i, amenitiesPicked.get(i));
                }
                
            }
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                String hotelId = rs.getString("hotel_id");
                String hotelName = rs.getString("hotel_name");
                String hotelTypeId = rs.getString("hotelType_id");
                String bgImage = rs.getString("background_image");
                String country = rs.getString("country");
                String city = rs.getString("city");
                String district = rs.getString("district");
                String address = rs.getString("address");
                int averagePrice = rs.getInt("average_price");
                int rating = rs.getInt("rating");
                HotelDTO hotel = new HotelDTO(hotelId, hotelName, hotelTypeId, bgImage, country, city, district, address, averagePrice, rating);
                
                listHotel.add(hotel);
            }
            
            return listHotel;
            
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
        
        return null;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        HotelDAO dao = new HotelDAO();
        System.out.println(dao.getAllHotelBasicInfo());
    }
}
