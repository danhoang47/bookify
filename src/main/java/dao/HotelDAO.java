/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dto.Hotel;

/**
 *
 * @author toten
 */
public class HotelDAO {

    static Connection conn;
    static PreparedStatement ps;
    static ResultSet rs;

    public static List<Hotel> listAll() {
        try {
            String query = "select * from hotel";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            List<Hotel> listHotel = new ArrayList<>();

            while (rs.next()) {

                listHotel.add(new Hotel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)==0 ? false : true, rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13)));
                
                
            }

            return listHotel;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static Hotel viewHotel(String hotel_id) {
        try {
            String query = "select * from hotel where hotel_id=?";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            ps.setString(1, hotel_id);

            rs = ps.executeQuery();


            while (rs.next()) {

                Hotel hotel = new Hotel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)==0 ? false : true, rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13));
                
                return hotel;
            }
            
            return null;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    public static boolean addNewHotel(Hotel hotel) {
        try {
            String query = "INSERT INTO Hotel VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            
            ps.setString(1, hotel.getHotelId());
            ps.setString(2, hotel.getUserId());
            ps.setString(3, hotel.getHotelTypeId());
            ps.setString(4, hotel.getHotelName());
            ps.setString(5, null);
            ps.setString(6, hotel.getBackgroundImg());
            ps.setInt(7, 0);
            ps.setString(8, hotel.getDescription());
            ps.setString(9, hotel.getCountry());
            ps.setString(10, hotel.getDistrict());
            ps.setString(11, hotel.getCity());
            ps.setString(12, hotel.getStreetName());
            ps.setString(13, hotel.getStreetNum());
            
            int a = ps.executeUpdate();
            
            if(a==1) {
                return true;
            } else {
                return false;
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static void main(String[] args) {
        HotelDAO htd = new HotelDAO();
        UUID uuid = UUID.randomUUID();
        List<Hotel> listHotel = htd.listAll();
        
        Hotel ht = new Hotel(uuid.toString(), "123", "314ec1bd-204b-4e8e-8b3a-098b4cc110fa", "Hotel name", null, "BG1.png", false, "this is a hotel", "Viet name", 
                "Hue", "Hue", "Nguyen Hue", "13");
        
        boolean s = htd.addNewHotel(ht);
        System.out.println(s);
    }
}
